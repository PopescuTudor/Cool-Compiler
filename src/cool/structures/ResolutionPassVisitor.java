package cool.structures;

import cool.ast.*;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResolutionPassVisitor  implements ASTVisitor<Void>{

    private DefaultScope defaultScope;

    public ResolutionPassVisitor(DefaultScope defaultScope) {
        this.defaultScope = defaultScope;
    }

    @Override
    public Void visit(Program program) {
        for(ClassNode classNode : program.classes){
            classNode.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        var classSymbol = (ClassSymbol) defaultScope.lookup(classNode.getName().getText());

        if (classSymbol == null) {
            return null;
        }
        checkInheritanceCycles(classSymbol, classNode);

        return null;

    }

    private void checkInheritanceCycles(ClassSymbol classSymbol, ClassNode classNode) {

        ClassSymbol current = classSymbol;
        Map<String, Boolean> dfsVisited = new HashMap<>();

        while (current != null) {
            String className = current.getName();
            if (dfsVisited.containsKey(className)) {
                SymbolTable.error(current.getCtx(), classNode.getName(),
                        "Inheritance cycle for class " + className);
                return;
            }
            dfsVisited.put(className, true);

            current = current.getParentClass();

        }
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
