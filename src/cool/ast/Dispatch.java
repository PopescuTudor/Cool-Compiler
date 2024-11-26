package cool.ast;

import java.util.List;

public class Dispatch extends Expression {
    private Expression obj;
    private String type;
    private String methodName;
    private List<Expression> args;

    public Dispatch(Expression obj, String type, String methodName, List<Expression> args) {
        this.obj = obj;
        this.type = type;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + ".");
        obj.print(indentation + 2);
        if (type != null) {
            System.out.println(" ".repeat(indentation + 2) + type);
        }
        System.out.println(" ".repeat(indentation + 2) + methodName);
        for (Expression arg : args) {
            arg.print(indentation + 2);
        }
    }
}
