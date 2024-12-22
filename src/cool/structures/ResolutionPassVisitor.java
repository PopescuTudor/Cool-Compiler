package cool.structures;

import cool.ast.*;
import org.antlr.v4.runtime.Token;

import java.util.*;

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

        Set<String> inheritedAttributes = getInheritedAttributes(classSymbol);

        for (Feature feature : classNode.getFeatures()) {
            if (feature instanceof Attribute) {
                var attribute = (Attribute) feature;

                if (inheritedAttributes.contains(attribute.getName().getText())) {
                    SymbolTable.error(attribute.getCtx(), attribute.getName(),
                            "Class " + classNode.getName().getText() + " redefines inherited attribute " + attribute.getName().getText());
                }

                feature.accept(this);

            } else if (feature instanceof Method) {
                feature.accept(this);
            }
        }

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
        var typeToken = attribute.getType();
        var typeName = typeToken.getText();

        var typeSymbol = defaultScope.lookup(typeName);
        if (typeSymbol == null && !attribute.isRedefined()) {
            SymbolTable.error(attribute.getCtx(), typeToken,
                    "Class " + attribute.getParentClass().getName() + " has attribute " +
                            attribute.getName().getText() + " with undefined type " + typeName);
        }

        return null;
    }

    private Set<String> getInheritedAttributes(ClassSymbol classSymbol) {
        Set<String> inheritedAttributes = new HashSet<>();
        Set<String> visitedClasses = new HashSet<>();
        ClassSymbol current = classSymbol.getParentClass();


        while (current != null) {
            if (visitedClasses.contains(current.getName())) {
                break;
            }
            visitedClasses.add(current.getName());

            for (Symbol symbol : current.getSymbols().values()) {

                if (symbol instanceof IdSymbol) {
                    inheritedAttributes.add(symbol.getName());
                }
            }
            current = current.getParentClass();
        }

        return inheritedAttributes;
    }


    @Override
    public Void visit(Method method) {
        var returnType = method.getReturnType().getText();
        var classSymbol = method.getParentClass();


        var typeSymbol = defaultScope.lookup(returnType);
        if (typeSymbol == null) {
            SymbolTable.error(method.getCtx(), method.getName(),
                    "Class " + (method.getParentClass()).getName() +
                            " has method " + method.getName().getText() +
                            " with undefined return type " + returnType);
        }

        ClassSymbol parentClass = classSymbol.getParentClass();

        while (parentClass != null) {

            var overriddenMethodSymbol = parentClass.lookup(method.getName().getText());

            if (overriddenMethodSymbol instanceof MethodSymbol) {
                var overriddenMethod = (MethodSymbol) overriddenMethodSymbol;
                if (method.getFormals().size() != overriddenMethod.getFormals().size()) {
                    SymbolTable.error(method.getCtx(), method.getName(),
                            "Class " + classSymbol.getName() + " overrides method " +
                                    method.getName().getText() + " with different number of formal parameters");
                    break;
                }

                List<Formal> overriddenFormals = overriddenMethod.getFormals();
                List<Formal> currentFormals = method.getFormals();


                for (int i = 0; i < currentFormals.size(); i++) {
                    Formal currentFormal = currentFormals.get(i);
                    Formal overriddenFormal = overriddenFormals.get(i);

                    if (!currentFormal.getType().getText().equals(overriddenFormal.getType().getText())) {
                        SymbolTable.error(currentFormal.getCtx(), currentFormal.getType(),
                                "Class " + classSymbol.getName() + " overrides method " +
                                        method.getName().getText() + " but changes type of formal parameter " +
                                        currentFormal.getName().getText() + " from " +
                                        overriddenFormal.getType().getText() + " to " +
                                        currentFormal.getType().getText());
                    }
                }

                String overriddenReturnType = overriddenMethod.getReturnType();
                String currentReturnType = method.getReturnType().getText();

                if (!currentReturnType.equals(overriddenReturnType)) {
                    SymbolTable.error(method.getCtx(), method.getReturnType(),
                            "Class " + classSymbol.getName() + " overrides method " +
                                    method.getName().getText() + " but changes return type from " +
                                    overriddenReturnType + " to " + currentReturnType);
                }

                break;
            }
            parentClass = parentClass.getParentClass();
        }

        if (method.getBody() != null) {
            method.getBody().accept(this);
        }

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
        binaryOp.getLeft().accept(this);
        binaryOp.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(While whileNode) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        for (LocalVarDef localVarDef : let.getLocalVarDefs()) {
            localVarDef.accept(this);
        }
        let.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(LocalVarDef localVarDef) {
        return null;
    }

    @Override
    public Void visit(Case caseNode) {
        if (caseNode.getExpr() != null) {
            caseNode.getExpr().accept(this);
        }

        for (CaseBranch branch : caseNode.getBranches()) {
            if(!(branch.hasSemanticError())) {
                branch.accept(this);
            }

        }

        return null;
    }

    @Override
    public Void visit(CaseBranch caseBranch) {
        var varName = caseBranch.getName().getText();
        var varType = caseBranch.getType().getText();

        var typeSymbol = defaultScope.lookup(varType);
        if (typeSymbol == null && !caseBranch.hasSemanticError()) {
            SymbolTable.error(caseBranch.getCtx(), caseBranch.getType(),
                    "Case variable " + varName + " has undefined type " + varType);
        }

        if (caseBranch.getBody() != null) {
            caseBranch.getBody().accept(this);
        }

        return null;
    }


    @Override
    public Void visit(Block block) {
        for (Expression expression : block.getExpressions()) {
            expression.accept(this);
        }
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
        isVoid.getExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(UnaryOp unaryOp) {
        unaryOp.getExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(Id id) {
        var idName = id.getName().getText();

        var symbol = id.getScope().lookup(idName);
        if (symbol == null) {
            SymbolTable.error(id.getCtx(), id.getToken(),
                    "Undefined identifier " + idName);
        }

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
        assign.getId().accept(this);
        assign.getExpr().accept(this);
        return null;
    }
}
