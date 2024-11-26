package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class ClassNode extends ASTNode {
    private String name;
    private String parent;
    private List<Feature> features;

    public ClassNode(Token token, String name, String parent, List<Feature> features) {
        super(token);
        this.name = name;
        this.parent = parent;
        this.features = features;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "class");
        System.out.println(" ".repeat(indentation + 2) + name);
        if (parent != null) {
            System.out.println(" ".repeat(indentation + 2) + parent);
        }
        for (Feature feature : features) {
            feature.print(indentation + 2);
        }
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
