package cool.structures;


public class TypeSymbol extends Symbol {
    public TypeSymbol(String name) {
        super(name);
    }

    public static final TypeSymbol INT   = new TypeSymbol("Int");
    public static final TypeSymbol STRING = new TypeSymbol("String");
    public static final TypeSymbol BOOL  = new TypeSymbol("Bool");
    public static final TypeSymbol SELF_TYPE = new TypeSymbol("SELF_TYPE");
    public static final TypeSymbol OBJECT = new TypeSymbol("Object");

    public TypeSymbol getType(String type) {
        return switch (type) {
            case "Int" -> INT;
            case "String" -> STRING;
            case "Bool" -> BOOL;
            case "SELF_TYPE" -> SELF_TYPE;
            case "Object" -> OBJECT;
            default -> null;
        };
    }
}

