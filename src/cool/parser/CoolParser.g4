parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header {
    package cool.parser;
}

program: (class SEMICOLON)+;

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
    | LET OBJECT_ID COLON TYPE_ID (ASSIGN expr)?
      (COMMA OBJECT_ID COLON TYPE_ID (ASSIGN expr)?)* IN expr               # let
    | CASE expr OF (OBJECT_ID COLON TYPE_ID DARROW expr SEMICOLON)+ ESAC    # case
    | NEW TYPE_ID                                                           # new
    | ISVOID expr                                                           # isvoid
    | expr (PLUS | MINUS | STAR | SLASH) expr                                 # binaryOp
    | (NOT | TILDE) expr                                                # unaryOp
    | expr PLUS expr                                                        # plus
    | expr MINUS expr                                                       # minus
    | expr STAR expr                                                        # mult
    | expr SLASH expr                                                       # div
    | TILDE expr                                                            # neg
    | expr LT expr                                                          # lt
    | expr LE expr                                                          # le
    | expr EQUALS expr                                                      # eq
    | NOT expr                                                              # not
    | LPAREN expr RPAREN                                                    # paren
    | OBJECT_ID                                                             # id
    | INT                                                                   # int
    | STRING                                                                # string
    | TRUE                                                                  # bool
    | FALSE                                                                 # bool
    | OBJECT_ID ASSIGN expr                                                 # assign
    ;