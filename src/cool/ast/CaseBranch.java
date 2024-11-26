package cool.ast;

import org.antlr.v4.runtime.Token;

public class CaseBranch extends ASTNode {
    private String name;
    private String type;
    private Expression body;

    public CaseBranch(Token token, String name, String type, Expression body) {
        super(token);
        this.name = name;
        this.type = type;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "case branch");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
        body.print(indentation + 2);
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
