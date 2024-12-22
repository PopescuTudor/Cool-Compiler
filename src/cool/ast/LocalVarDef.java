package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class LocalVarDef extends ASTNode {
    private Token name;
    private Token type;
    private Expression init;

    public LocalVarDef(ParserRuleContext ctx, Token token, Token name, Token type, Expression init) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.init = init;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "local");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
        if (init != null) {
            init.print(indentation + 2);
        }
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public Expression getInit() {
        return init;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
