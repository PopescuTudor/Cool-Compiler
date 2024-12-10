package cool.ast;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IsVoid extends Expression {
    private Expression expr;

    public IsVoid(ParserRuleContext ctx, Token token, Expression expr) {
        super(token, ctx);
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "isvoid");
        expr.print(indentation + 2);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
