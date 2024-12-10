package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IntConstant extends Expression {
    private int value;

    public IntConstant(ParserRuleContext ctx, Token token, int value) {
        super(token, ctx);
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + value);
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
