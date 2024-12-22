package cool.ast;

import cool.structures.ClassSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Attribute extends Feature {
    private Token name;
    private Token type;
    private Expression init;
    protected ClassSymbol parentClass;
    protected boolean redefined = false;

    public Attribute(ParserRuleContext ctx, Token token, Token name, Token type, Expression init) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.init = init;
    }

    @Override
    public void print(int indentation) {
        System.out.println(" ".repeat(indentation) + "attribute");
        System.out.println(" ".repeat(indentation + 2) + name);
        System.out.println(" ".repeat(indentation + 2) + type);
        if (init != null) {
            init.print(indentation + 2);
        }
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public Expression getInit() {
        return init;
    }

    public void setParentClass(ClassSymbol parentClass) {
        this.parentClass = parentClass;
    }

    public ClassSymbol getParentClass() {
        return parentClass;
    }

    public void setRedefined(boolean redefined) {
        this.redefined = redefined;
    }

    public boolean isRedefined() {
        return redefined;
    }
}
