lexer grammar CoolLexer;

@header {
    package cool.lexer;
}

@members {
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

// Keywords
CLASS: 'class';
ELSE: 'else';
FALSE: 'false';
FI: 'fi';
IF: 'if';
IN: 'in';
INHERITS: 'inherits';
ISVOID: 'isvoid';
LET: 'let';
LOOP: 'loop';
POOL: 'pool';
THEN: 'then';
WHILE: 'while';
CASE: 'case';
ESAC: 'esac';
NEW: 'new';
OF: 'of';
NOT: 'not';
TRUE: 'true';

// Symbols
SEMICOLON: ';';
COLON: ':';
COMMA: ',';
DOT: '.';
AT: '@';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';
TILDE: '~';
LT: '<';
LE: '<=';
GE: '>=';
GT: '>';
EQUALS: '=';
DARROW: '=>';
ASSIGN: '<-';

// Identifiers
TYPE_ID: [A-Z][a-zA-Z0-9_]*;
OBJECT_ID: [a-z][a-zA-Z0-9_]*;

// Integer
INT: [0-9]+;

// String
STRING: '"' (ESC | ~["\\])* '"' {
    String content = getText().substring(1, getText().length() - 1);
    content = content.replace("\\n", "\n")
                     .replace("\\t", "\t")
                     .replace("\\b", "\b")
                     .replace("\\f", "\f");

    if (content.length() > 1024) {
        raiseError("String constant too long");
    } else if (content.contains("\0")) {
        raiseError("String contains null character");
    } else if (content.contains("\n")) {
        raiseError("Unterminated string constant");
    } else {
        setText(content);
    }
};

fragment ESC: '\\' [btnf"\\];

// Comments
UNMATCHED_COMMENT: '*)' { raiseError("Unmatched *)"); };

BLOCK_COMMENT
    : '(*' (BLOCK_COMMENT | .)*? ('*)' | EOF) {
        if (_input.LA(1) == EOF) {
            setType(ERROR);
            setText("EOF in comment");
        } else {
            skip();
        }
    };

// Whitespace
WS: [ \t\r\n\f]+ -> skip;

// Error handling
ERROR: . { raiseError("Invalid character: " + getText()); };