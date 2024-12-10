package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class ASTNode {

    protected Token token;
    protected ParserRuleContext ctx;

    public ASTNode(Token token, ParserRuleContext ctx) {
        this.token = token;
        this.ctx = ctx;
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }

    public abstract void print(int indentation);

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }

    public Token getToken() {
        return token;
    }

    public ParserRuleContext getCtx() {
        return ctx;
    }

}
