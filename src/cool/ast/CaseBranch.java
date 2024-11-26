package cool.ast;

public class CaseBranch extends ASTNode {
    private String name;
    private String type;
    private Expression body;

    public CaseBranch(String name, String type, Expression body) {
        this.name = name;
        this.type = type;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "case branch : " + name + " " + type);
        body.print(indentation + 2);
    }
}
