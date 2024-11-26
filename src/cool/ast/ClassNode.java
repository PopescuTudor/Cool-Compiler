package cool.ast;

import java.util.List;

public class ClassNode extends ASTNode {
    private String name;
    private String parent;
    private List<Feature> features;

    public ClassNode(String name, String parent, List<Feature> features) {
        this.name = name;
        this.parent = parent;
        this.features = features;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "class : " + name + (parent != null ? " " + parent : ""));
        for (Feature feature : features) {
            feature.print(indentation + 2);
        }
    }
}
