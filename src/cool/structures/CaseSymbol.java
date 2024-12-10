package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaseSymbol extends Symbol implements Scope {
    protected Scope parent;
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();

    public CaseSymbol(String name, Scope parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if (symbols.containsKey(sym.getName())) {
            return false;
        }
        symbols.put(sym.getName(), sym);
        return true;
    }

    @Override
    public Symbol lookup(String name) {
        Symbol sym = symbols.get(name);
        if (sym != null) {
            return sym;
        }
        if (parent != null) {
            return parent.lookup(name);
        }
        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}
