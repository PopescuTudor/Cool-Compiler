package cool.structures;

import cool.ast.*;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
    private DefaultScope defaultScope;
    private Scope currentScope;

    public DefaultScope getScope() {
        return defaultScope;
    }

    @Override
    public Void visit(Program program) {

        defaultScope = new DefaultScope(null);
        defaultScope.add(new ClassSymbol("Int", defaultScope));
        defaultScope.add(new ClassSymbol("String", defaultScope));
        defaultScope.add(new ClassSymbol("Bool", defaultScope));

        for (ClassNode classNode : program.classes) {
            var id = classNode.getName();
            var classSymbol = new ClassSymbol(id.getText(), defaultScope);
            classSymbol.setCtx(classNode.getCtx());

            if (!defaultScope.add(classSymbol)) {
                SymbolTable.error(classNode.getCtx(), id, "Class " + id.getText() + " is redefined");
                classSymbol.setProblematic(true);
            }
        }

        for (ClassNode classNode : program.classes) {
            var id = classNode.getName();
            var classSymbol = (ClassSymbol) defaultScope.lookup(id.getText());

            if (classNode.getParent() != null && !classSymbol.isProblematic()) {
                var parentName = classNode.getParent().getText();
                var parentSymbol = (ClassSymbol) defaultScope.lookup(parentName);

                if (parentSymbol == null) {
                    SymbolTable.error(classNode.getCtx(), classNode.getParent(),
                            "Class " + id.getText() + " has undefined parent " + parentName);
                    classSymbol.setProblematic(true);
                } else if (parentName.equals("Int") || parentName.equals("String") ||
                        parentName.equals("Bool") || parentName.equals("SELF_TYPE")) {
                    SymbolTable.error(classNode.getCtx(), classNode.getParent(),
                            "Class " + id.getText() + " has illegal parent " + parentName);
                    classSymbol.setProblematic(true);
                } else {
                    classSymbol.setParentClass(parentSymbol);
                }
            }
        }

        for (ClassNode classNode : program.classes) {
            classNode.accept(this);
        }
        return null;
    }


    @Override
    public Void visit(ClassNode classNode) {
        var id = classNode.getName();

        // class illegal name
        if(classNode.getName().getText().equals("SELF_TYPE")) {
            SymbolTable.error(classNode.getCtx(), classNode.getName(), "Class has illegal name " + id.getText());
            return null;
        }

        for (Feature feature : classNode.getFeatures()) {
            feature.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Attribute attribute) {
        return null;
    }

    @Override
    public Void visit(Method method) {
        return null;
    }

    @Override
    public Void visit(Formal formal) {
        return null;
    }

    @Override
    public Void visit(If ifNode) {
        return null;
    }

    @Override
    public Void visit(BinaryOp binaryOp) {
        return null;
    }

    @Override
    public Void visit(While whileNode) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        return null;
    }

    @Override
    public Void visit(LocalVarDef localVarDef) {
        return null;
    }

    @Override
    public Void visit(Case caseNode) {
        return null;
    }

    @Override
    public Void visit(CaseBranch caseBranch) {
        return null;
    }

    @Override
    public Void visit(Block block) {
        return null;
    }

    @Override
    public Void visit(Dispatch dispatch) {
        return null;
    }

    @Override
    public Void visit(ImplicitDispatch implicitDispatch) {
        return null;
    }

    @Override
    public Void visit(New newNode) {
        return null;
    }

    @Override
    public Void visit(IsVoid isVoid) {
        return null;
    }

    @Override
    public Void visit(UnaryOp unaryOp) {
        return null;
    }

    @Override
    public Void visit(Id id) {
        return null;
    }

    @Override
    public Void visit(IntConstant intConstant) {
        return null;
    }

    @Override
    public Void visit(StringConstant stringConstant) {
        return null;
    }

    @Override
    public Void visit(BoolConstant boolConstant) {
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        return null;
    }
}
