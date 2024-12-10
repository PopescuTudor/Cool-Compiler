package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LetSymbol extends Symbol implements Scope {
    protected Scope parent;
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    protected Map<String, Symbol> vars = new LinkedHashMap<>();

    public LetSymbol(String name, Scope parent) {
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

    public boolean addVar(Symbol sym) {
        if (vars.containsKey(sym.getName())) {
            return false;
        }
        vars.put(sym.getName(), sym);
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

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Symbol lookupVar(String name) {
        Symbol sym = vars.get(name);
        if (sym != null) {
            return sym;
        }
        if (parent != null) {
            return parent.lookup(name);
        }
        return null;
    }

    public Map<String, Symbol> getVars() {
        return vars;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
}
