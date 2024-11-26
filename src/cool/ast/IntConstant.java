package cool.ast;

public class IntConstant extends Expression {
    private int value;

    public IntConstant(int value) {
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + value);
    }}
