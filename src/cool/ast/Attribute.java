package cool.ast;

public class Attribute extends Feature {
    private String name;
    private String type;
    private Expression init;

    public Attribute(String name, String type, Expression init) {
        this.name = name;
        this.type = type;
        this.init = init;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "attribute : " + name + " " + type);
        if (init != null) {
            init.print(indentation + 2);
        }
    }
}
