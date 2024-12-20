package cool.structures;

import org.antlr.v4.runtime.Token;

public class IdSymbol extends Symbol {

    protected Token type;

    public IdSymbol(String name) {
        super(name);
    }

    public Token getType() {
        return type;
    }

    public void setType(Token type) {
        this.type = type;
    }
}
