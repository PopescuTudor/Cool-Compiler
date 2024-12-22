package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class CaseBranch extends ASTNode {
    private Token name;
    private Token type;
    private Expression body;

    public CaseBranch(ParserRuleContext ctx, Token token, Token name, Token type, Expression body) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "case branch");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
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

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public Expression getBody() {
        return body;
    }

    private boolean semanticError = false;

    public void setSemanticError(boolean semanticError) {
        this.semanticError = semanticError;
    }

    public boolean hasSemanticError() {
        return semanticError;
    }
}
