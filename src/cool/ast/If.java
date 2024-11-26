package cool.ast;

import org.antlr.v4.runtime.Token;

public class If extends Expression {
    private Expression condition;
    private Expression thenBranch;
    private Expression elseBranch;

    public If(Token token, Expression condition, Expression thenBranch, Expression elseBranch) {
        super(token);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "if");
        condition.print(indentation + 2);
        thenBranch.print(indentation + 2);
        elseBranch.print(indentation + 2);
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
