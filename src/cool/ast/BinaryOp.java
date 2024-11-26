package cool.ast;

import org.antlr.v4.runtime.Token;

public class BinaryOp extends Expression {
    private String op;
    private Expression left;
    private Expression right;

    public BinaryOp(Token token, String op, Expression left, Expression right) {
        super(token);
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

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
