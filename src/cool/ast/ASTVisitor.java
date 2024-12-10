package cool.ast;

import org.antlr.v4.runtime.Token;

public interface ASTVisitor<T> {
    T visit(Program program);
    T visit(ClassNode classNode);
    T visit(Attribute attribute);
    T visit(Method method);
    T visit(Formal formal);
    T visit(If ifNode);
    T visit(BinaryOp binaryOp);
    T visit(While whileNode);
    T visit(Let let);
    T visit(LocalVarDef localVarDef);
    T visit(Case caseNode);
    T visit(CaseBranch caseBranch);
    T visit(Block block);
    T visit(Dispatch dispatch);
    T visit(ImplicitDispatch implicitDispatch);
    T visit(New newNode);
    T visit(IsVoid isVoid);
    T visit(UnaryOp unaryOp);
    T visit(Id id);
    T visit(IntConstant intConstant);
    T visit(StringConstant stringConstant);
    T visit(BoolConstant boolConstant);
    T visit(Assign assign);


}
