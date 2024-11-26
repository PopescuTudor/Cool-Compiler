package cool.ast;

import java.util.List;

public class Let extends Expression {
    private List<LocalVarDef> localVarDefs;
    private Expression body;

    public Let(List<LocalVarDef> localVarDefs, Expression body) {
        this.localVarDefs = localVarDefs;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "let");
        for (LocalVarDef def : localVarDefs) {
            def.print(indentation + 2);
        }
        body.print(indentation + 2);
    }
}
