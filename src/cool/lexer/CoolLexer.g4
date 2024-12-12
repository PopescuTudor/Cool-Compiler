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

STRING
    : '"' (ESC | ~["\\])* '"' {
        String content = getText().substring(1, getText().length() - 1);
        if (content.indexOf('\u0000') != -1) {
            raiseError("String contains null character");
        } else {
            StringBuilder processed = new StringBuilder();
            boolean escaped = false;
            for (int i = 0; i < content.length(); i++) {
                char c = content.charAt(i);
                if (escaped) {
                    switch (c) {
                        case 't': processed.append('\t'); break;
                        case 'n': processed.append('\n'); break;
                        case 'b': processed.append('\b'); break;
                        case 'f': processed.append('\f'); break;
                        case '\\': processed.append('\\'); break;
                        default: processed.append(c); break;
                    }
                    escaped = false;
                } else if (c == '\\') {
                    escaped = true;
                } else {
                    processed.append(c);
                }
            }
            if (processed.length() > 1024) {
                raiseError("String constant too long");
            }
            setText(processed.toString());
        }
    };


fragment ESC : '\\' ([tnbf"\\] | .);
fragment NEW_LINE : '\r'? '\n';
UNTERMINATED_STRING : '"' (ESC | ~["\\\n])* NEW_LINE {
    raiseError("Unterminated string constant");
};

EOF_STRING : '"' (ESC | ~["\\\n])* EOF {
    raiseError("EOF in string constant");
};

// Comments
LINE_COMMENT: '--' ~[\r\n]* -> skip;
UNMATCHED_COMMENT: '*)' { raiseError("Unmatched *)"); };

BLOCK_COMMENT
    : '(*' (BLOCK_COMMENT | .)*? ('*)' | EOF) {
        if (_input.LA(1) == EOF) {
            raiseError("EOF in comment");
        } else {
            skip();
        }
    };

// Whitespace
WS: [ \t\r\n\f]+ -> skip;

// Error handling
ERROR: . { raiseError("Invalid character: " + getText()); };