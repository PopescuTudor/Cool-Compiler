package cool.ast;

public class If extends Expression {
    private Expression condition;
    private Expression thenBranch;
    private Expression elseBranch;

    public If(Expression condition, Expression thenBranch, Expression elseBranch) {
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
}
