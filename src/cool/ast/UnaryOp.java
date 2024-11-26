package cool.ast;

import org.antlr.v4.runtime.Token;

public class UnaryOp extends Expression {
    private String op;
    private Expression expr;


    public UnaryOp(Token token, String op, Expression expr) {
        super(token);
        this.op = op;
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + op);
        expr.print(indentation + 2);
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
