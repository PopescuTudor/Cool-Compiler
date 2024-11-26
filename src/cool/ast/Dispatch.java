package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Dispatch extends Expression {
    private Expression obj;
    private String type;
    private String methodName;
    private List<Expression> args;

    public Dispatch(Token token, Expression obj, String type, String methodName, List<Expression> args) {
        super(token);
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

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
