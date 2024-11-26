package cool.ast;

import org.antlr.v4.runtime.Token;

public class LocalVarDef extends ASTNode {
    private String name;
    private String type;
    private Expression init;

    public LocalVarDef(Token token, String name, String type, Expression init) {
        super(token);
        this.name = name;
        this.type = type;
        this.init = init;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "local");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
        if (init != null) {
            init.print(indentation + 2);
        }
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
