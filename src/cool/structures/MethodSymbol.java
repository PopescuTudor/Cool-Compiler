package cool.structures;

import cool.ast.Formal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MethodSymbol extends IdSymbol implements Scope{
    protected Scope parent;
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    private List<Formal> formals;
    private String returnType;

    public MethodSymbol(Scope parent, String name, List<Formal> formals) {
        super(name);
        this.parent = parent;
        this.formals = formals;
    }

    @Override
    public boolean add(Symbol sym) {
        if (symbols.containsKey(sym.getName()))
            return false;

        symbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String str) {
        var sym = symbols.get(str);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.lookup(str);

        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public List<Formal> getFormals() {
        return formals;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
