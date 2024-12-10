package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Id extends Expression {
    private String name;

    public Id(ParserRuleContext ctx, Token token, String name) {
        super(token, ctx);
        this.name = name;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + name);
    }

    public String getName() {
        return token.getText();
    }

    public int getLine() {
        return token.getLine();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

