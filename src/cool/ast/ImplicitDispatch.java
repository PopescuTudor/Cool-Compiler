package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class ImplicitDispatch extends Expression {
    private String methodName;
    private List<Expression> args;

    public ImplicitDispatch(Token token, String methodName, List<Expression> args) {
        super(token);
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "implicit dispatch");
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
