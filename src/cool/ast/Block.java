package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Block extends Expression {
    private List<Expression> expressions;

    public Block(Token token, List<Expression> expressions) {
        super(token);
        this.expressions = expressions;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "block");
        for (Expression expr : expressions) {
            expr.print(indentation + 2);
        }
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
