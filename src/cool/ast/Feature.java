package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Feature extends ASTNode {
    public Feature(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }
}
