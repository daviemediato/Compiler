
package compiler;

import compiler.lexer.Lexer;
import java.io.*;

public class Compiler {

    public static void main(String[] args) {
        try {
            Lexer lexer = new Lexer("./teste1.txt");

            lexer.scanAll();

        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

}
