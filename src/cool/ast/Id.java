package cool.ast;

import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Id extends Expression {
    private Token name;
    private Scope scope;

    public Id(ParserRuleContext ctx, Token token, Token name) {
        super(token, ctx);
        this.name = name;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + name);
    }

    public Token getName() {
        return name;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public int getLine() {
        return token.getLine();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

