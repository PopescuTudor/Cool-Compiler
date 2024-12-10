package cool.structures;

import java.util.*;

public class DefaultScope implements Scope {
    
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    
    protected Scope parent;
    
    public DefaultScope(Scope parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        // verificam daca simbolul exista domeniul de vizibilitate
        if (symbols.containsKey(sym.getName()))
            return false;
        
        symbols.put(sym.getName(), sym);
        
        return true;
    }

    @Override
    public Symbol lookup(String name) {
        var sym = symbols.get(name);
        
        if (sym != null)
            return sym;

        // cautam in domeniul de mai sus
        if (parent != null)
            return parent.lookup(name);
        
        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
    
    @Override
    public String toString() {
        return symbols.values().toString();
    }

}
