package cool.ast;

public class IsVoid extends Expression {
    private Expression expr;

    public IsVoid(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "isvoid");
        expr.print(indentation + 2);
    }
}
