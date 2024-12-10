package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class While extends Expression {
    private Expression condition;
    private Expression body;

    public While(ParserRuleContext ctx, Token token, Expression condition, Expression body) {
        super(token, ctx);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "while");
        condition.print(indentation + 2);
        body.print(indentation + 2);
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
}
