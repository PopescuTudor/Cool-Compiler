package cool.ast;

public class StringConstant extends Expression {
    private String value;

    public StringConstant(String value) {
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "\"" + value + "\"");
    }
}
