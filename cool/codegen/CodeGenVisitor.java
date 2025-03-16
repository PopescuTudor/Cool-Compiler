package cool.codegen;

import cool.ast.ASTVisitor;
import cool.ast.nodes.*;
import cool.ast.nodes.Class;
import cool.semantics.structures.GlobalScope;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.io.PrintStream;
import java.util.*;

public class CodeGenVisitor implements ASTVisitor<Void> {
    private Program     root;
    private GlobalScope globalScope;
    private PrintStream out;


    private LinkedHashMap<String,Integer> stringIndices = new LinkedHashMap<>();
    private int nextStringIndex = 1;

    private LinkedHashMap<Integer,Integer> intIndices   = new LinkedHashMap<>();
    private int nextIntIndex = 1;

    private List<ST> strConstantTemplates = new ArrayList<>();
    private List<ST> intConstantTemplates = new ArrayList<>();

    private List<String> classNameLines = new ArrayList<>();
    private List<String> objTabLines    = new ArrayList<>();

    private List<ST> protoObjects     = new ArrayList<>();
    private List<ST> dispatchVectors  = new ArrayList<>();
    private List<ST> functions        = new ArrayList<>();

    private Map<String,Integer> classTags = new HashMap<>();
    private Map<String,String>  parentMap = new HashMap<>();

    private Map<String, Class> classMap = new HashMap<>();

    private STGroupFile stGroup;

    private List<String> currentMethodInstructions = new ArrayList<>();
    private String currentClassName = null;
    private String currentMethodName = null;

    static class ClassInfo {
        String  name;
        String  parent;
        Class   astNode;
        List<Feature> features;

        public ClassInfo(String name, String parent, Class astNode) {
            this.name = name;
            this.parent = parent;
            this.astNode = astNode;
            if (astNode == null) {
                this.features = new ArrayList<>();
            } else {
                this.features = astNode.features;
            }
        }
    }

    public CodeGenVisitor(Program root, GlobalScope globalScope, PrintStream out) {
        this.root        = root;
        this.globalScope = globalScope;
        this.out         = out;
        stGroup = new STGroupFile("cool/codegen/codegen.stg");
    }


    public void generateCode() {
        for (Class c : root.classes) {
            classMap.put(c.name.getText(), c);
        }

        List<ClassInfo> allClasses = new ArrayList<>();
        Map<String, String> parentMap = new HashMap<>();
        Map<String, List<String>> childrenMap = new HashMap<>();

        allClasses.add(new ClassInfo("Object", null, null));
        allClasses.add(new ClassInfo("IO", "Object", null));
        allClasses.add(new ClassInfo("Int", "Object", null));
        allClasses.add(new ClassInfo("String", "Object", null));
        allClasses.add(new ClassInfo("Bool", "Object", null));

        for (Class c : root.classes) {
            String clsName = c.name.getText();
            String parentName = (c.inherits != null) ? c.inherits.getText() : "Object";
            allClasses.add(new ClassInfo(clsName, parentName, c));
        }

        for (ClassInfo ci : allClasses) {
            parentMap.put(ci.name, ci.parent);
            childrenMap.putIfAbsent(ci.name, new ArrayList<>());
        }
        for (ClassInfo ci : allClasses) {
            String p = ci.parent;
            if (p != null) {
                childrenMap.putIfAbsent(p, new ArrayList<>());
                childrenMap.get(p).add(ci.name);
            }
        }

        List<String> sortedClasses = new ArrayList<>();
        Queue<String> Q = new LinkedList<>();
        Q.offer("Object");
        while (!Q.isEmpty()) {
            String cls = Q.poll();
            sortedClasses.add(cls);
            for (String child : childrenMap.getOrDefault(cls, Collections.emptyList())) {
                Q.offer(child);
            }
        }

        for (int i = 0; i < sortedClasses.size(); i++) {
            String clsName = sortedClasses.get(i);
            classTags.put(clsName, i);
        }

        for (String clsName : sortedClasses) {
            if (classMap.containsKey(clsName)) {
                makeUserClass(classMap.get(clsName));
            }
            else {
                makeBuiltInClass(clsName);
            }
        }

        defineStringAndIntConsts();

        ST programST = stGroup.getInstanceOf("assembleProgram");
        programST.add("strConstants", strConstantTemplates);
        programST.add("intConstants", intConstantTemplates);

        ST classNameST = stGroup.getInstanceOf("lines");
        classNameST.add("itemList", classNameLines);
        programST.add("classNameEntries", classNameST.render());

        ST objTabST = stGroup.getInstanceOf("lines");
        objTabST.add("itemList", objTabLines);
        programST.add("initAndObjTab", objTabST.render());

        programST.add("prototypes", protoObjects);
        programST.add("dispatchTables", dispatchVectors);
        programST.add("functions", functions);

        out.println(programST.render());
    }

    private void makeBuiltInClass(String clsName) {
        classNameLines.add("    .word " + getStrConstLabel(clsName));
        objTabLines.add("    .word " + clsName + "_protObj");
        objTabLines.add("    .word " + clsName + "_init");

        protoObjects.add(makeProtoObjectForBuiltIn(
                clsName,
                classTags.get(clsName),
                /*size=*/3,
                Collections.emptyList()
        ));

        dispatchVectors.add(dispTab(clsName, Arrays.asList(
                "    .word Object.abort",
                "    .word Object.type_name",
                "    .word Object.copy"
        )));
    }

    private ST makeProtoObjectForBuiltIn(String clsName, int tag, int size, List<String> attrs) {
        ST st = stGroup.getInstanceOf("protoObject");
        st.add("clsName",   clsName);
        st.add("tagVal",    tag);
        st.add("sizeWords", size);
        st.add("attrs",     attrs);
        return st;
    }


    private void makeUserClass(Class c) {
        String clsName = c.name.getText();

        classNameLines.add("    .word " + getStrConstLabel(clsName));
        objTabLines.add("    .word " + clsName + "_protObj");
        objTabLines.add("    .word " + clsName + "_init");

        protoObjects.add(makeProtoObject(clsName));

        dispatchVectors.add(dispTab(clsName, Arrays.asList(
                "    .word Object.abort",
                "    .word Object.type_name",
                "    .word Object.copy"
        )));

        ST initST = stGroup.getInstanceOf("initRoutine");
        initST.add("clsName", clsName);
        initST.add("parentName", parentMap.getOrDefault(clsName, "Object"));
        functions.add(initST);

        if ("Main".equals(clsName)) {
            for (Feature feature : c.features) {
                if (feature instanceof FunctionDefinition) {
                    FunctionDefinition fn = (FunctionDefinition) feature;
                    if ("main".equals(fn.name.getText())) {
                        currentClassName = clsName;
                        currentMethodName = "main";
                        currentMethodInstructions.clear();

                        if (fn.functionValue != null) {
                            fn.functionValue.accept(this);
                        }

                        ST methodST = stGroup.getInstanceOf("methodDef");
                        methodST.add("labelName", "Main.main");
                        methodST.add("instructions", String.join("\n", currentMethodInstructions));
                        functions.add(methodST);

                        currentClassName = null;
                        currentMethodName = null;
                        break;
                    }
                }
            }
        }
    }

    private ST makeProtoObject(String clsName) {
        int tag = classTags.get(clsName);
        List<VariableDefinition> attrs = getAllAttributes(clsName);

        int sizeWords = 3 + attrs.size();
        List<String> attrLines = new ArrayList<>();
        for (VariableDefinition v : attrs) {
            String defaultVal = getLabelForDefault(v);
            attrLines.add("    .word " + defaultVal);
        }

        ST st = stGroup.getInstanceOf("protoObject");
        st.add("clsName",   clsName);
        st.add("tagVal",    tag);
        st.add("sizeWords", sizeWords);
        st.add("attrs",     attrLines);
        return st;
    }

    private List<VariableDefinition> getAllAttributes(String clsName) {
        List<VariableDefinition> result = new ArrayList<>();
        String parent = parentMap.get(clsName);

        if (parent != null && classMap.containsKey(parent)) {
            result.addAll(getAllAttributes(parent));
        }

        Class cnode = classMap.get(clsName);
        if (cnode != null && cnode.features != null) {
            for (Feature f : cnode.features) {
                if (f instanceof VariableDefinition) {
                    result.add((VariableDefinition) f);
                }
            }
        }
        return result;
    }

    private String getLabelForDefault(VariableDefinition v) {
        if (v.variableValue == null) {
            String typeName = v.type.getText();
            switch (typeName) {
                case "Int":
                    return "int_const0";
                case "String":
                    return "str_const0";
                case "Bool":
                    return "bool_const0";
                default:
                    return "0";
            }
        }

        Expression init = v.variableValue;
        if (init instanceof Int) {
            int val = Integer.parseInt(((Int)init).name.getText());
            return getIntConstLabel(val);
        }
        else if (init instanceof StringNode) {
            String txt = ((StringNode)init).name.getText();
            return getStrConstLabel(txt);
        }
        else if (init instanceof BoolNode) {
            boolean b = "true".equals(((BoolNode)init).name.getText());
            return b ? "bool_const1" : "bool_const0";
        }

        return "0";
    }


    private void defineStringAndIntConsts() {
        getStrConstLabel("Abort called");

        for (Map.Entry<String,Integer> e : stringIndices.entrySet()) {
            String text = e.getKey();
            int idx     = e.getValue();

            ST st = stGroup.getInstanceOf("stringConstant");
            st.add("idx",       idx);
            st.add("tagVal",    3);
            st.add("sizeWords", 5);

            String lengthLabel = getIntConstLabel(text.length());
            st.add("lengthConst", lengthLabel.replaceAll("\\D+", ""));
            st.add("content", text);

            strConstantTemplates.add(st);
        }

        for (Map.Entry<Integer,Integer> e : intIndices.entrySet()) {
            int value = e.getKey();
            int idx   = e.getValue();

            ST st = stGroup.getInstanceOf("intConstant");
            st.add("idx", idx);
            st.add("tagVal", 2);
            st.add("intVal", value);

            intConstantTemplates.add(st);
        }
    }

    private String getStrConstLabel(String text) {
        if (!stringIndices.containsKey(text)) {
            stringIndices.put(text, nextStringIndex++);
        }
        return "str_const" + stringIndices.get(text);
    }

    private String getIntConstLabel(int val) {
        if (val == 0) {
            return "int_const0";
        }
        if (!intIndices.containsKey(val)) {
            intIndices.put(val, nextIntIndex++);
        }
        return "int_const" + intIndices.get(val);
    }

    private ST dispTab(String clsName, List<String> linesArr) {
        ST st = stGroup.getInstanceOf("dispatchVector");
        st.add("clsName", clsName);
        st.add("linesArr", linesArr);
        return st;
    }

    @Override
    public Void visit(Program p) {
        for (Class c : p.classes) {
            c.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Class c) {
        currentClassName = c.name.getText();

        if (c.features != null) {
            for (Feature f : c.features) {
                f.accept(this);
            }
        }
        currentClassName = null;
        return null;
    }

    @Override
    public Void visit(VariableDefinition v) {
        if (v.variableValue != null) {
            v.variableValue.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FunctionDefinition f) {
        currentMethodName = f.name.getText();
        currentMethodInstructions.clear();

        if (f.functionValue != null) {
            f.functionValue.accept(this);
        }

        ST methodST = stGroup.getInstanceOf("methodDef");
        methodST.add("labelName", currentClassName + "." + currentMethodName);
        String body = String.join("\n", currentMethodInstructions);
        methodST.add("instructions", body);
        functions.add(methodST);

        currentMethodInstructions.clear();
        currentMethodName = null;
        return null;
    }

    @Override
    public Void visit(Call c) {
        if (c.args != null) {
            for (Expression arg : c.args) {
                arg.accept(this);
            }
        }

        if ("abort".equals(c.name.getText())) {
            boolean explicitSelf = c.prefix != null && isSelf(c.prefix);

            if (c.prefix == null || explicitSelf) {
                currentMethodInstructions.add("    move    $a0 $s0");
                String dispatchLabel = freshLabel("dispatch");
                currentMethodInstructions.add("    bnez    $a0 " + dispatchLabel);
                currentMethodInstructions.add("    la      $a0 " + getStrConstLabel("Abort called"));
                currentMethodInstructions.add("    li      $t1 28");
                currentMethodInstructions.add("    jal     _dispatch_abort");
                currentMethodInstructions.add(dispatchLabel + ":");
                currentMethodInstructions.add("    lw      $t1 8($a0)");
                currentMethodInstructions.add("    lw      $t1 0($t1)");
                currentMethodInstructions.add("    jalr    $t1");
            } else {
                c.prefix.accept(this);
                String dispatchLabel = freshLabel("dispatch");
                currentMethodInstructions.add("    bnez    $a0 " + dispatchLabel);
                currentMethodInstructions.add("    la      $a0 " + getStrConstLabel("Abort called"));
                currentMethodInstructions.add("    li      $t1 28");
                currentMethodInstructions.add("    jal     _dispatch_abort");
                currentMethodInstructions.add(dispatchLabel + ":");
                currentMethodInstructions.add("    lw      $t1 8($a0)");
                currentMethodInstructions.add("    lw      $t1 0($t1)");
                currentMethodInstructions.add("    jalr    $t1");
            }
        } else {
            if (c.prefix != null) {
                c.prefix.accept(this);
            }
        }

        return null;
    }

    private boolean isSelf(Expression prefix) {
        return (prefix instanceof Id) && "self".equals(((Id)prefix).name.getText());
    }

    @Override public Void visit(Assign a) {
        if (a.expr != null) {
            a.expr.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ImplicitCall c) {
        if (c.args != null) {
            for (Expression arg : c.args) {
                arg.accept(this);
            }
        }

        String methodName = c.name.getText();
        if ("abort".equals(methodName)) {

            currentMethodInstructions.add("    move    $a0 $s0");
            String dispatchLabel = freshLabel("dispatch");
            currentMethodInstructions.add("    bnez    $a0 " + dispatchLabel);
            currentMethodInstructions.add("    la      $a0 " + getStrConstLabel("Abort called"));
            currentMethodInstructions.add("    li      $t1 28");
            currentMethodInstructions.add("    jal     _dispatch_abort");
            currentMethodInstructions.add(dispatchLabel + ":");
            currentMethodInstructions.add("    lw      $t1 8($a0)");
            currentMethodInstructions.add("    lw      $t1 0($t1)");
            currentMethodInstructions.add("    jalr    $t1");
        }
        else {
            currentMethodInstructions.add("# Implicit call to " + methodName);
        }

        return null;
    }


    @Override public Void visit(If i) {
        i.cond.accept(this);
        i.thenBranch.accept(this);
        i.elseBranch.accept(this);
        return null;
    }

    @Override public Void visit(While w) {
        w.cond.accept(this);
        w.action.accept(this);
        return null;
    }

    @Override public Void visit(LocalParam lp) {
        if (lp.value != null) lp.value.accept(this);
        return null;
    }

    @Override public Void visit(Let l) {
        l.params.forEach(p -> p.accept(this));
        l.action.accept(this);
        return null;
    }

    @Override public Void visit(Case c) {
        c.caseExpr.accept(this);
        c.caseBranches.forEach(cb -> cb.accept(this));
        return null;
    }

    @Override public Void visit(CaseBranch cb) {
        cb.value.accept(this);
        return null;
    }

    @Override public Void visit(Block b) {
        for (Expression ex : b.actions) {
            ex.accept(this);
        }
        return null;
    }

    @Override public Void visit(Id id) {
        return null;
    }

    @Override public Void visit(FormalParam f) {
        return null;
    }

    @Override public Void visit(Int i) {
        return null;
    }

    @Override public Void visit(StringNode s) {
        return null;
    }

    @Override public Void visit(BoolNode b) {
        return null;
    }

    @Override public Void visit(BinaryOperator o) {
        return null;
    }

    @Override public Void visit(UnaryOperator o) {
        return null;
    }

    @Override public Void visit(New n) {
        return null;
    }

    private String freshLabel(String base) {
        return base + UUID.randomUUID().toString().replace("-", "");
    }
}