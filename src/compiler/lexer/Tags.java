package compiler.lexer;


/* Classe que define as constantes para os tokens. */

public class Tags {
    public final static int 
            
            // PALAVRAS RESERVADAS
            
            INIT = 256,
            STOP = 257,
            STRING = 258,
            FLOAT = 259,
            INT = 260,
            IF = 261,
            ELSE = 262,
            DO = 263,
            WHILE = 264,
            READ = 265,
            WRITE = 266,
            CLASS = 267,
            LITERAL = 268,
            
            // OPERADORES
            
            ADD = 277,
            SUB = 278,
            OR = 279, /* || */
            MUL = 280,
            AND = 281, /* && */
            DIV = 282,
            GT = 283,
            LT = 284,
            GE = 285,
            LE = 286,
            EQ = 287,
            DIF = 288,
            EXCL = 289, /* NOT OU FACTOR */
            ASSIGN = 290,
            
            // OUTROS
            
            LPAR = 300,
            RPAR = 301,
            LKEY = 302,
            RKEY = 303,
            SCOL = 304,
            COMMA = 305,
            DOT = 306,
            QUOT = 307,
            LINECOM = 308,
            LBCOM = 309,
            RBCOM = 310,
            ID = 311,
            
            // FIM DO ARQUIVO
            
            EOF = 65535;
            
}
