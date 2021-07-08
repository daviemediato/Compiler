
package compiler.lexer;


public class Word extends Token{
    public String lexema;
    
    public static final Word
            
            // PALAVRAS RESERVADAS
            
            init = new Word("init", Tags.INIT),
            stop = new Word("stop", Tags.STOP),
            word = new Word("string", Tags.STRING),
            real = new Word("float", Tags.FLOAT),
            num = new Word("int", Tags.INT),
            ifWord = new Word("if", Tags.IF),
            elseWord = new Word("else", Tags.ELSE),
            doWord = new Word("do", Tags.DO),
            whileWord = new Word("while", Tags.WHILE),
            read = new Word("read", Tags.READ),
            write = new Word("write", Tags.WRITE),
            classWord = new Word("class", Tags.CLASS),
            
            // OPERADORES
            
            add = new Word("+", Tags.ADD),
            sub = new Word("-", Tags.SUB),
            or = new Word("||", Tags.OR),
            mul = new Word("*", Tags.MUL),
            and = new Word("&&", Tags.AND),
            div = new Word("/", Tags.DIV),
            gt = new Word(">", Tags.GT),
            lt = new Word("<", Tags.LT),
            ge = new Word(">=", Tags.GE),
            le = new Word("<=", Tags.LE),
            eq = new Word("==", Tags.EQ),
            dif = new Word("!=", Tags.DIF),
            excl = new Word("!", Tags.EXCL),
            assign = new Word("=", Tags.ASSIGN),
            
            // OUTROS
            
            lpar = new Word("(", Tags.LPAR),
            rpar = new Word(")", Tags.RPAR),
            lkey = new Word("{", Tags.LKEY),
            rkey = new Word("}", Tags.RKEY),
            scol = new Word(";", Tags.SCOL),
            comma = new Word(",", Tags.COMMA),
            dot = new Word(".", Tags.DOT),
            quot = new Word("\"", Tags.QUOT),
            linecom = new Word("//", Tags.LINECOM),
            lbcom = new Word("/*", Tags.LBCOM),
            rbcom = new Word("*/", Tags.RBCOM);
    
    

    public Word(String lexema, int tag) {
        super(tag);
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "Word{" + "lexema = " + lexema + '}';
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    
    
    
}
