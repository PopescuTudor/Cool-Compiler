package cool.ast;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Assign extends Expression {
    private Id id;
    private Expression expr;

    public Assign(ParserRuleContext ctx, Token start, Id id, Expression expr) {
        super(start, ctx);
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

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Id getId() {
        return id;
    }

    public Expression getExpr() {
        return expr;
    }
}
