package cool.ast;

public class BoolConstant extends Expression {
    private boolean value;

    public BoolConstant(boolean value) {
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + value);
    }
}
