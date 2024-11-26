package cool.ast;

import org.antlr.v4.runtime.Token;

public abstract class Feature extends ASTNode {
    public Feature(Token token) {
        super(token);
    }
}
