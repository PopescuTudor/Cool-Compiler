package cool.ast;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;


public class Case extends Expression {
    private Expression expr;
    private List<CaseBranch> branches;

    public Case(ParserRuleContext ctx, Token token, Expression expr, List<CaseBranch> branches) {
        super(token, ctx);
        this.expr = expr;
        this.branches = branches;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "case");
        expr.print(indentation + 2);
        for (CaseBranch branch : branches) {
            branch.print(indentation + 2);
        }
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
}
