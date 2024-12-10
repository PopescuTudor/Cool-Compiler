package cool.structures;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends Symbol implements Scope {
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    protected Map<String, Symbol> methods = new LinkedHashMap<>();
    protected Scope parent;

    public ClassSymbol(String name, Scope parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if(sym instanceof MethodSymbol) {
            if (methods.containsKey(sym.getName())) {
                return false;
            }
            methods.put(sym.getName(), sym);
        } else {
            if (symbols.containsKey(sym.getName())) {
                return false;
            }
            symbols.put(sym.getName(), sym);
        }
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

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return name;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}