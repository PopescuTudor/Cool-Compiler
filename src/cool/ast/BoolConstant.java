package cool.ast;

import org.antlr.v4.runtime.Token;

public class BoolConstant extends Expression {
    private boolean value;

    public BoolConstant(Token token, boolean value) {
        super(token);
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + value);
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
