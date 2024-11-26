package cool.ast;

public class New extends Expression {
    private String type;

    public New(String type) {
        this.type = type;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + ": new : " + type);
    }
}
