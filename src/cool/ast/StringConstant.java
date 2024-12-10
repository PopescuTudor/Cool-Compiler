package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class StringConstant extends Expression {
    private String value;

    public StringConstant(ParserRuleContext ctx, Token token, String value) {
        super(token, ctx);
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + value);
    }

    public String getValue() {
        return token.getText();
    }

    public int getLine() {
        return token.getLine();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
