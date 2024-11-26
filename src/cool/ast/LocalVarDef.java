package cool.ast;

public class LocalVarDef extends ASTNode {
    private String name;
    private String type;
    private Expression init;

    public LocalVarDef(String name, String type, Expression init) {
        this.name = name;
        this.type = type;
        this.init = init;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "local");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
        if (init != null) {
            init.print(indentation + 2);
        }
    }
}
