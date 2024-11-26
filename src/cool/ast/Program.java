package cool.ast;

import java.util.List;

public class Program extends ASTNode {
    private List<ClassNode> classes;

    public Program(List<ClassNode> classes) {
        this.classes = classes;
    }

    @Override
    public void print(int indentation) {
        System.out.println("program");
        for (ClassNode classNode : classes) {
            classNode.print(indentation + 2);
        }
    }
}
