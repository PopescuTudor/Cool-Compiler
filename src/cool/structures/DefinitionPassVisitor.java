package cool.structures;

import cool.ast.*;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
    private Scope currentScope;


    @Override
    public Void visit(Program program) {

        currentScope = new DefaultScope(null);
        currentScope.add(new ClassSymbol("Int", currentScope));
        currentScope.add(new ClassSymbol("String", currentScope));
        currentScope.add(new ClassSymbol("Bool", currentScope));

        for (ClassNode classNode : program.classes) {
            var id = classNode.getName();
            var classSymbol = new ClassSymbol(id.getText(), currentScope);
            if (!currentScope.add(new ClassSymbol(id.getText(), currentScope))) {
                SymbolTable.error(classNode.getCtx(), id, "Class " + id.getText() + " is redefined");
            } else {
                currentScope.add(classSymbol);
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
        // class redefinition
        var classSymbol = new ClassSymbol(id.getText(), currentScope);
        currentScope = classSymbol;

        // class illegal parent
        if (classNode.getParent() != null) {
            var parent = classNode.getParent();
            if (parent.getText().equals("Int") || parent.getText().equals("String") || parent.getText().equals("Bool")
                    || parent.getText().equals("SELF_TYPE")) {
                SymbolTable.error(classNode.getCtx(), classNode.getParent(), "Class " + id.getText() +
                        " has illegal parent " + parent.getText());
                currentScope = currentScope.getParent();
                return null;
            }
            var parentSymbol = currentScope.lookup(parent.getText());
            if (parentSymbol == null) {
                SymbolTable.error(classNode.getCtx(), classNode.getParent(), "Class " + id.getText() +
                        " has undefined parent " + parent.getText());
                return null;
            }
        }




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
