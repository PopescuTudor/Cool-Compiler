package cool.ast;

import org.antlr.v4.runtime.Token;

public class Assign extends Expression {
    private String id;
    private Expression expr;

    public Assign(Token start, String id, Expression expr) {
        super(start);
        this.id = id;
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "<-");
        System.out.println(" ".repeat(indentation + 2) + id);
        expr.print(indentation + 2);
    }

    public int getLine() {
        return token().getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
