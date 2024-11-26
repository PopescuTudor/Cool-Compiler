package cool.ast;

public class While extends Expression {
    private Expression condition;
    private Expression body;

    public While(Expression condition, Expression body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "while");
        condition.print(indentation + 2);
        body.print(indentation + 2);
    }
}
