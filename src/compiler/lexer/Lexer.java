package compiler.lexer;

import java.io.*;
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char chInput = ' ';
    private Hashtable words = new Hashtable();
    private FileReader file;

    private void reserve(Word word) {
        this.words.put(word.getLexema(), word);
    }

    public Lexer(String file) throws FileNotFoundException, Exception {
        try {

            // Lê o arquivo
            this.file = new FileReader(file);

            // Palavras reservadas
            reserve(Word.init);
            reserve(Word.stop);
            reserve(Word.word);
            reserve(Word.real);
            reserve(Word.num);
            reserve(Word.ifWord);
            reserve(Word.elseWord);
            reserve(Word.doWord);
            reserve(Word.whileWord);
            reserve(Word.read);
            reserve(Word.write);
            reserve(Word.classWord);

            // Operadores
            reserve(Word.add);
            reserve(Word.sub);
            reserve(Word.or);
            reserve(Word.mul);
            reserve(Word.and);
            reserve(Word.div);
            reserve(Word.gt);
            reserve(Word.lt);
            reserve(Word.ge);
            reserve(Word.le);
            reserve(Word.eq);
            reserve(Word.dif);
            reserve(Word.excl);
            reserve(Word.assign);

            // Outros
            reserve(Word.lpar);
            reserve(Word.rpar);
            reserve(Word.lkey);
            reserve(Word.rkey);
            reserve(Word.scol);
            reserve(Word.comma);
            reserve(Word.dot);
            reserve(Word.quot);
            reserve(Word.linecom);
            reserve(Word.lbcom);
            reserve(Word.rbcom);

        } catch (FileNotFoundException error) {
            System.out.println("File not found: " + error.getMessage());
            throw error;
        } catch (Exception error) {
            System.out.println("Error reading file: " + error.getMessage());
            throw error;
        }
    }

    private void readch() throws IOException {
        int input = this.file.read();
        if (input == -1) // EOF
            throw new IOException("End of file");
        else
            this.chInput = (char) input;
    }

    private boolean readch(char ch) throws IOException {
        this.readch();

        if (this.chInput != ch) {
            return false;
        }
        this.chInput = ' ';
        return true;
    }

    public ArrayList<Token> scanAll() throws Exception {
        ArrayList<Token> tokenArr = new ArrayList<>();
        Token token;

        try {
            do {
                token = this.scan();
                tokenArr.add(token);
                System.out.println(token.toString());

                if (token.tag == Tags.ERROR) {
                    throw new Exception("lexer error in line " + this.line);
                }

            } while (token.tag != Tags.EOF);
        } catch (Exception e) {
            System.out.println("Error in Scan All: " + e.getMessage());
        } finally {
            return tokenArr;
        }
    }

    private void ignoreComment(Boolean inline) throws IOException {
        try {
            if (inline) {
                while (!this.readch('\n'))
                    ;
                line++;
            } else {
                char prevCh = chInput;
                this.readch();
                System.out.println("Started ignoreCommentBlock - " + prevCh + "-" + chInput);
                while (!(prevCh == '*' && chInput == '/')) {
                    // System.out.println("-- " + chInput);
                    this.readch();
                    if (this.chInput == '\n')
                        line++;

                    prevCh = this.chInput;
                }
            }
        } catch (IOException e) {
            throw new IOException("Missing closing comment in line " + this.line); // Error IO - Closing Comment
        }
        System.out.println("Finished ignoreCommentBlock - " + chInput);
    }

    public Token scan() throws IOException {

        // Verificando delimitadores de entrada
        for (;; this.readch()) {
            if (this.chInput == ' ' || this.chInput == '\t' || this.chInput == '\r' || this.chInput == '\b') {
                continue;
            } else if (this.chInput == '\n') {
                line++;
            } else {
                break;
            }
        }

        // Verificando operadores/outros
        switch (this.chInput) {
            case '&':
                if (this.readch('&')) {
                    return Word.and;
                } else {
                    return new Token('&');
                }
            case '|':
                if (this.readch('|')) {
                    return Word.or;
                } else {
                    return new Token('|');
                }
            case '=':
                if (this.readch('=')) {
                    return Word.eq;
                } else {
                    return Word.assign;
                }
            case '<':
                if (this.readch('=')) {
                    return Word.le;
                } else {
                    return Word.lt;
                }
            case '>':
                if (this.readch('=')) {
                    return Word.ge;
                } else {
                    return Word.gt;
                }
            case ';':
                this.readch();
                return Word.scol;
            case '(':
                this.readch();
                return Word.lpar;
            case ')':
                this.readch();
                return Word.rpar;
            case '!':
                if (this.readch('=')) {
                    return Word.dif;
                } else {
                    return Word.excl;
                }
            case '+':
                this.readch();
                return Word.add;
            case '-':
                this.readch();
                return Word.sub;
            case ',':
                this.readch();
                return Word.comma;
            case '.':
                this.readch();
                return Word.dot;
            case '{':
                this.readch();
                return Word.rkey;
            case '}':
                this.readch();
                return Word.lkey;
            // Comentarios
            case '*':
                this.readch();
                if (this.chInput == '/') {
                    return Word.rbcom;
                } else {
                    return Word.mul;
                }
            case '/':
                this.readch();
                if (this.chInput == '*') {
                    this.ignoreComment(false);
                    return Word.lbcom;
                } else if (this.chInput == '/') {
                    this.ignoreComment(true);
                    return Word.linecom;
                } else {
                    return Word.div;
                }
                // Strings (Literals)
            case '"':
                this.readch();
                return Word.quot;
        }

        // Números
        if (Character.isDigit(this.chInput)) {
            Integer value = 0;

            do {
                value = (10 * value) + Character.digit(this.chInput, 10);
                this.readch();

            } while (Character.isDigit(this.chInput));

            if (this.chInput == '.') {
                Integer decimal = 0;
                this.readch();

                if (Character.isDigit(this.chInput)) {
                    do {
                        decimal = (10 * decimal) + Character.digit(this.chInput, 10);
                        this.readch();

                    } while (Character.isDigit(this.chInput));
                } else {
                    return new Token(Tags.ERROR); // Retorna erro float --> 10.bla
                }

                String floatResult = value.toString() + '.' + decimal.toString();
                float floatValue = Float.parseFloat(floatResult);
                return new Real(floatValue);
            }

            return new Num(value);
        }

        // Identificadores
        if (Character.isLetter(this.chInput) || this.chInput == '_') {
            StringBuffer sb = new StringBuffer();

            do {
                sb.append(this.chInput);
                this.readch();

            } while (Character.isLetterOrDigit(this.chInput) || this.chInput == '_');

            String string = sb.toString();
            Word word = (Word) words.get(string);
            if (word != null) {
                return word;
            }

            word = new Word(string, Tags.ID);
            words.put(string, word);
            return word;
        }

        // Não especificados
        Token t = new Token(this.chInput);
        this.chInput = ' ';
        return t;
    }

}
