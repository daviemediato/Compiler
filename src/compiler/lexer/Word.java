
package compiler.lexer;


public class Word extends Token{
    public String lexema;
    public String description="";
    
    public static final Word
            
            // PALAVRAS RESERVADAS
            
            init = new Word("init", Tags.INIT, "Init"),
            stop = new Word("stop", Tags.STOP, "Stop"),
            word = new Word("string", Tags.LITERAL, "Literal"),
            real = new Word("float", Tags.FLOAT, "Float"),
            num = new Word("int", Tags.INT, "Int"),
            ifWord = new Word("if", Tags.IF, "If"),
            elseWord = new Word("else", Tags.ELSE, "Else"),
            doWord = new Word("do", Tags.DO, "Do"),
            whileWord = new Word("while", Tags.WHILE, "While"),
            read = new Word("read", Tags.READ, "Read"),
            write = new Word("write", Tags.WRITE, "Write"),
            classWord = new Word("class", Tags.CLASS, "Class"),
            
            // OPERADORES
            
            add = new Word("+", Tags.ADD, "Plus Sign"),
            sub = new Word("-", Tags.SUB, "Minus Sign"),
            or = new Word("||", Tags.OR, "Or"),
            mul = new Word("*", Tags.MUL, "Multiplication Sign"),
            and = new Word("&&", Tags.AND, "And"),
            div = new Word("/", Tags.DIV, "Division Sign"),
            gt = new Word(">", Tags.GT, "GT"),
            lt = new Word("<", Tags.LT, "LT"),
            ge = new Word(">=", Tags.GE, "GE"),
            le = new Word("<=", Tags.LE, "LE"),
            eq = new Word("==", Tags.EQ, "EQ"),
            dif = new Word("!=", Tags.DIF, "Difference"),
            excl = new Word("!", Tags.EXCL, "Exclamation Mark"),
            assign = new Word("=", Tags.ASSIGN, "Equals Sign"),
            
            // OUTROS
            
            lpar = new Word("(", Tags.LPAR, "Left Parentheses"),
            rpar = new Word(")", Tags.RPAR, "Right Parentheses"),
            lkey = new Word("{", Tags.LKEY, "Left Key"),
            rkey = new Word("}", Tags.RKEY, "Right Key"),
            scol = new Word(";", Tags.SCOL, "SemiColon"),
            comma = new Word(",", Tags.COMMA, "Comma"),
            dot = new Word(".", Tags.DOT, "Dot"),
            
            // BLOCOS
            quot = new Word("\"", Tags.QUOT),
            linecom = new Word("//", Tags.LINECOM),
            lbcom = new Word("/*", Tags.LBCOM),
            rbcom = new Word("*/", Tags.RBCOM);
            
    

    public Word(String lexema, int tag) {
        super(tag);
        this.lexema = lexema;
    }

    public Word(String lexema, int tag, String description) {
        super(tag);
        this.lexema = lexema;
        this.description = description;
    }

    @Override
    public String toString() {
        if(this.description == "")
            return "Word    " + "lexema = " + lexema + " \ttag = " + tag + " ";
        else
            return "Word    " + "lexema = " + lexema + " \ttag = " + tag + " \tdescription = " + description + " ";
    }
    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    
    
    
}
