package cool.ast;

import org.antlr.v4.runtime.Token;

public class IsVoid extends Expression {
    private Expression expr;

    public IsVoid(Token token, Expression expr) {
        super(token);
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "isvoid");
        expr.print(indentation + 2);
    }
}
