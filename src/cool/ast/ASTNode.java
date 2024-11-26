package cool.ast;

import org.antlr.v4.runtime.Token;

public abstract class ASTNode {

    protected Token token;

    public ASTNode(Token token) {
        this.token = token;
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
    public abstract void print(int indentation);
}
