package compiler.sintatic;

import compiler.lexer.Lexer;
import compiler.lexer.Token;

import java.io.IOException;

public class Sintatic {
	private Lexer lexer;
	private Token token;


    public Sintatic (String path) {
		try{
   	    	this.lexer = new Lexer(path);
		} catch(Exception e){
			// Lexer already printed error message
		}
    }
	
	public Token getToken() throws IOException{
        return lexer.scan();
    }

	public void advance(){
		try{
			this.token = this.getToken();
		} catch(Exception e){
			System.out.println("Syntax error - Unexpected Token: " + e.getMessage());
		}
	}

	public void eat(int t){
			if(this.token.tag == t){
				this.advance();
			} else{
				System.out.println("Syntax error - Expected Token with tag " + t + " but found " + this.token.tag);
			}

	}




}
