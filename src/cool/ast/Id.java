package cool.ast;

public class Id extends Expression {
    private String name;

    public Id(String name) {
        this.name = name;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + name);
    }
}
