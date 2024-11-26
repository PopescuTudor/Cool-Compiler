package cool.ast;

public class BinaryOp extends Expression {
    private Expression left;
    private String op;
    private Expression right;

    public BinaryOp(Expression left, String op, Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + op);
        left.print(indentation + 2);
        right.print(indentation + 2);
    }
}
