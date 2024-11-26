package cool.ast;

import org.antlr.v4.runtime.Token;

public class Formal extends ASTNode {
    private String name;
    private String type;

    public Formal(Token token, String name, String type) {
        super(token);
        this.name = name;
        this.type = type;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "formal");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
    }

    public String getName() {
        return token.getText();
    }

    public String getType() {
        return token.getText();
    }
}
