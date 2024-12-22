package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Formal extends ASTNode {
    private Token name;
    private Token type;

    public Formal(ParserRuleContext ctx, Token token, Token name, Token type) {
        super(token, ctx);
        this.name = name;
        this.type = type;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "formal");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
