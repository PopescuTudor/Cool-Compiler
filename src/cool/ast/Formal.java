package cool.ast;

public class Formal extends ASTNode {
    private String name;
    private String type;

    public Formal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "formal : " + name + " " + type);
    }
}
