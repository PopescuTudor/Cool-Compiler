parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header {
    package cool.parser;
}

program: (class SEMICOLON)+ EOF;

class: CLASS TYPE_ID (INHERITS TYPE_ID)? LBRACE (feature SEMICOLON)* RBRACE;

feature
    : OBJECT_ID LPAREN (formal (COMMA formal)*)? RPAREN COLON TYPE_ID LBRACE expr RBRACE  # method
    | OBJECT_ID COLON TYPE_ID (ASSIGN expr)?                                               # attribute
    ;

formal: OBJECT_ID COLON TYPE_ID;

expr
    : expr (AT TYPE_ID)? DOT OBJECT_ID LPAREN (expr (COMMA expr)*)? RPAREN  # dispatch
    | OBJECT_ID LPAREN (expr (COMMA expr)*)? RPAREN                         # implicitDispatch
    | IF expr THEN expr ELSE expr FI                                        # if
    | WHILE expr LOOP expr POOL                                             # while
    | LBRACE (expr SEMICOLON)+ RBRACE                                       # block
    | LET let_decl (COMMA let_decl)* IN expr                                # let
    | CASE expr OF (OBJECT_ID COLON TYPE_ID DARROW expr SEMICOLON)+ ESAC    # case
    | NEW TYPE_ID                                                           # new
    | ISVOID expr                                                           # isvoid
    | expr (STAR | SLASH) expr                                              # binaryOp
    | expr (PLUS | MINUS) expr                                              # binaryOp
    | expr (LE | LT | EQUALS | GE | GT) expr                                # comparison
    | (NOT | TILDE) expr                                                    # unaryOp
    | LPAREN expr RPAREN                                                    # paren
    | OBJECT_ID ASSIGN expr                                                 # assign
    | OBJECT_ID                                                             # id
    | INT                                                                   # int
    | STRING                                                                # string
    | TRUE                                                                  # bool
    | FALSE                                                                 # bool
    ;

let_decl
    : OBJECT_ID COLON TYPE_ID (ASSIGN expr)?                             # letDecl
    ;