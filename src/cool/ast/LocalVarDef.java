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
        System.out.println(" ".repeat(indentation) + "local : " + name + " " + type);
        if (init != null) {
            init.print(indentation + 2);
        }
    }
}
