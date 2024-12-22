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
                // Visit each method
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
