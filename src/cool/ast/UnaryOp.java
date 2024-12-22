package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class UnaryOp extends Expression {
    private String op;
    private Expression expr;


    public UnaryOp(ParserRuleContext ctx, Token token, String op, Expression expr) {
        super(token, ctx);
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

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getOp() {
        return op;
    }

    public Expression getExpr() {
        return expr;
    }
}
