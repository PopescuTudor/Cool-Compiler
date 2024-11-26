package cool.ast;

import java.util.List;

public class Case extends Expression {
    private Expression expr;
    private List<CaseBranch> branches;

    public Case(Expression expr, List<CaseBranch> branches) {
        this.expr = expr;
        this.branches = branches;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "case");
        expr.print(indentation + 2);
        for (CaseBranch branch : branches) {
            branch.print(indentation + 2);
        }
    }
}
