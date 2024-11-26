package cool.ast;

public class UnaryOp extends Expression {
    private String op;
    private Expression expr;

    public UnaryOp(String op, Expression expr) {
        this.op = op;
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + ": " + op + " :");
        expr.print(indentation + 2);
    }
}
