package cool.ast;

import java.util.List;

public class Method extends Feature {
    private String name;
    private List<Formal> formals;
    private String returnType;
    private Expression body;

    public Method(String name, List<Formal> formals, String returnType, Expression body) {
        this.name = name;
        this.formals = formals;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "method");
        System.out.println(" ".repeat(indentation + 2) + name);
        for (Formal formal : formals) {
            formal.print(indentation + 2);
        }
        System.out.println(" ".repeat(indentation + 2) + returnType);
        body.print(indentation + 2);
    }
}
