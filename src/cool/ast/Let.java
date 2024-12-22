package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Let extends Expression {
    private List<LocalVarDef> localVarDefs;
    private Expression body;

    public Let(ParserRuleContext ctx, Token token, List<LocalVarDef> localVarDefs, Expression body) {
        super(token, ctx);
        this.localVarDefs = localVarDefs;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "let");
        for (LocalVarDef def : localVarDefs) {
            def.print(indentation + 2);
        }
        body.print(indentation + 2);
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }

    public List<LocalVarDef> getLocalVarDefs() {
        return localVarDefs;
    }

    public Expression getBody() {
        return body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
