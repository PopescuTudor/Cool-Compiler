package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Program extends ASTNode {
    private List<ClassNode> classes;

    public Program(Token token, List<ClassNode> classes) {
        super(token);
        this.classes = classes;
    }

    @Override
    public void print(int indentation) {
        System.out.println("program");
        for (ClassNode classNode : classes) {
            classNode.print(indentation + 2);
        }
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
