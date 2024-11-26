package cool.ast;

import java.util.List;

public class Block extends Expression {
    private List<Expression> expressions;

    public Block(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "block");
        for (Expression expr : expressions) {
            expr.print(indentation + 2);
        }
    }
}
