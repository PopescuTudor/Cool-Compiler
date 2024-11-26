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
        System.out.println(" ".repeat(indentation) + "case branch");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
        body.print(indentation + 2);
    }
}
