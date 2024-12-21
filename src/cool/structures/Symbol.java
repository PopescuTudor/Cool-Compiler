package cool.structures;

import org.antlr.v4.runtime.ParserRuleContext;

public class Symbol {
    protected String name;
    private ParserRuleContext ctx;
    protected boolean problematic = false;

    public boolean isProblematic() {
        return problematic;
    }

    public void setProblematic(boolean problematic) {
        this.problematic = problematic;
    }

    public void setCtx(ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    public ParserRuleContext getCtx() {
        return ctx;
    }
    
    public Symbol(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
