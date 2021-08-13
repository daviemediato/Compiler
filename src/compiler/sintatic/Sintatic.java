package compiler.sintatic;

import compiler.lexer.Lexer;
import compiler.lexer.Token;
import compiler.lexer.Tags;

import java.io.IOException;

public class Sintatic {

    private Lexer lexer;
    private Token token;

    public Sintatic(String path) {
        try {
            this.lexer = new Lexer(path);
        } catch (Exception e) {
            System.out.println("Error in Sintace constructor: " + e.getMessage());
        }
    }

    public Token getToken() throws IOException {
        return lexer.scan();
    }

    public void advance() {
        try {
            this.token = this.getToken();
        } catch (Exception e) {
            System.out.println("Error in advance: " + e.getMessage());
        }
    }

    public void eat(int t, String error) throws IOException {
        if (this.token.tag == t) {
            this.advance();
        } else {
            this.error(error);
        }

    }
    
    public void error(String errorRule) throws IOException{
        throw new IOException("Syntax error in rule" + errorRule + "in line" + this.lexer.line);
    }
    
    public void run() throws IOException {
        // primeiro token
        this.advance();
        
        if (this.token.tag == Tags.CLASS) {
            this.program();
            // eat?
        } else {
            this.error("inicio?"); // qual nome aq
        }
    }
    
    public void program () throws IOException { 
        if (this.token.tag == Tags.CLASS) {
            this.eat(Tags.CLASS, "Program");
            this.eat(Tags.ID, "Program");
            this.program2();
        } else {
            this.error("Program");
        }
    }
    
    public void program2() throws IOException {
        switch (this.token.tag) {
            case Tags.INT:
            case Tags.STRING:
            case Tags.FLOAT:
                this.decList();
                this.body();
                break;
            case Tags.INIT:
                this.body();
                break;
            default:
                this.error("Program2");
                break;
             
        }
    }
    
    public void decList()throws IOException {
        switch(this.token.tag) {
            case Tags.INT:
            case Tags.STRING:
            case Tags.FLOAT:
                this.decl();
                eat(Tags.SCOL, "decList");
                this.decList2();
            default:
                this.error("decList");
                break;
        }
    }
    
    public void decList2() throws IOException {
        switch(this.token.tag) {
            case Tags.INT:
            case Tags.STRING:
            case Tags.FLOAT:
                this.decl();
                eat(Tags.SCOL, "decList2");
                this.decList2();
            default:
                this.error("decList2");
                break;
        }
    }
    
    public void decl() throws IOException {
        
    }
    
    public void body()throws IOException {
        
    }

}
