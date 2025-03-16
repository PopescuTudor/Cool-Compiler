## COOL Language Compiler
### This project implements a complete compiler for the COOL programming language in Java, using ANTLR for parser generation. The compiler covers three essential stages:

Lexical and Syntax Analysis: Parses COOL source files, generating an Abstract Syntax Tree (AST), and reporting lexical or syntax errors clearly with descriptive messages.

Semantic Analysis: Validates type correctness, class inheritance, and resolves symbols using symbol tables, annotating the AST and reporting semantic errors when necessary.

Code Generation: Translates validated ASTs into executable MIPS assembly code, compatible with SPIM simulators, leveraging runtime support libraries for dynamic memory management and standard class operations.

This compiler provides detailed error messages and supports testing with multiple input files.