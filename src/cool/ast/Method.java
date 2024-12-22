package cool.ast;

import cool.structures.ClassSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Method extends Feature {
    private Token name;
    private List<Formal> formals;
    private Token returnType;
    private Expression body;
    protected ClassSymbol parentClass;
    private boolean redefined = false;

    public Method(ParserRuleContext ctx, Token token, Token name, List<Formal> formals, Token returnType, Expression body) {
        super(token, ctx);
        this.name = name;
        this.formals = formals;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "method");
        System.out.println(" ".repeat(indentation + 2) + name);
        for (Formal formal : formals) {
            formal.print(indentation + 2);
        }
        System.out.println(" ".repeat(indentation + 2) + returnType);
        body.print(indentation + 2);
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Token getName() {
        return name;
    }

    public List<Formal> getFormals() {
        return formals;
    }

    public Token getReturnType() {
        return returnType;
    }

    public Expression getBody() {
        return body;
    }

    public void setParentClass(ClassSymbol parentClass) {
        this.parentClass = parentClass;
    }

    public ClassSymbol getParentClass() {
        return parentClass;
    }

    public boolean isRedefined() {
        return redefined;
    }

    public void setRedefined(boolean redefined) {
        this.redefined = redefined;
    }
}
