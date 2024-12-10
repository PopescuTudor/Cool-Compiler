package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class MethodSymbol extends IdSymbol implements Scope{
    protected Scope parent;
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();

    public MethodSymbol(Scope parent, String name) {
        super(name);
        this.parent = parent;
//        this.isGlobal = false;
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
}
