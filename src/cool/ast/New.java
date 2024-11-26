package cool.ast;

import org.antlr.v4.runtime.Token;

public class New extends Expression {
    private String type;

    public New(Token token, String type) {
        super(token);
        this.type = type;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "new");
        System.out.println(" ".repeat(indentation + 2) + type);
    }

    public String getType() {
        return token.getText();
    }

    public int getLine() {
        return token.getLine();
    }
}
