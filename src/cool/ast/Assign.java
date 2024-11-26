package cool.ast;

public class Assign extends Expression {
    private String id;
    private Expression expr;

    public Assign(String id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + ": <- : " + id);
        expr.print(indentation + 2);
    }
}
