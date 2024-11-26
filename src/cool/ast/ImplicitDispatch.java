package cool.ast;

import java.util.List;

public class ImplicitDispatch extends Expression {
    private String methodName;
    private List<Expression> args;

    public ImplicitDispatch(String methodName, List<Expression> args) {
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + ": implicit dispatch : " + methodName);
        for (Expression arg : args) {
            arg.print(indentation + 2);
        }
    }
}
