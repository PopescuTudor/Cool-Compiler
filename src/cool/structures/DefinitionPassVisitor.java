package cool.structures;

import cool.ast.*;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
    private Scope currentScope;


    @Override
    public Void visit(Program program) {

        currentScope = new DefaultScope(null);
        currentScope.add(TypeSymbol.INT);
        currentScope.add(TypeSymbol.STRING);
        currentScope.add(TypeSymbol.BOOL);


        for (ClassNode classNode : program.classes) {
            classNode.accept(this);
        }
        return null;
    }


    @Override
    public Void visit(ClassNode classNode) {
        var id = classNode.getName();
//        var type = new TypeSymbol(id.getText());
        // class illegal name
        if(classNode.getName().equals("SELF_TYPE")) {
            SymbolTable.error(classNode.getCtx(), classNode.getToken(), "Class has illegal name " + id);
            return null;
        }
        // class redefinition
        var classSymbol = new ClassSymbol(id, currentScope);
        if(!currentScope.add(classSymbol)) {
            SymbolTable.error(classNode.getCtx(), classNode.getToken(), "Class " +  id + " is redefined");
            return null;
        }

        // class illegal parent
        if (classNode.getParent() != null) {
            var parent = classNode.getParent();
            if (parent.equals("Int") || parent.equals("String") || parent.equals("Bool") || parent.equals("SELF_TYPE")) {
                SymbolTable.error(classNode.getCtx(), classNode.getToken(), "Class " + id + " has illegal parent " + parent);
                currentScope = classSymbol;
                return null;
            }
//            var parentSymbol = currentScope.lookup(parent);
//            if (parentSymbol == null) {
//                SymbolTable.error(classNode.getCtx(), classNode.getToken(), "Class " + id + " has undefined parent " + parent);
//                currentScope = classSymbol;
//                return null;
//            }
        }



        currentScope = classSymbol;

        for (Feature feature : classNode.getFeatures()) {
            feature.accept(this);
        }

        currentScope = currentScope.getParent();
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
