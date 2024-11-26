package cool.ast;

public class BinaryOp extends Expression {
    private String op;
    private Expression left;
    private Expression right;

    public BinaryOp(String op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + op);
        left.print(indentation + 2);
        right.print(indentation + 2);
    }
}
