package cool.ast;

import org.antlr.v4.runtime.Token;

public class StringConstant extends Expression {
    private String value;

    public StringConstant(Token token, String value) {
        super(token);
        this.value = value;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + value);
    }

    public String getValue() {
        return token.getText();
    }

    public int getLine() {
        return token.getLine();
    }
}
