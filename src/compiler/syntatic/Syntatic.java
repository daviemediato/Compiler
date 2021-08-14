package compiler.syntatic;

import compiler.lexer.Lexer;
import compiler.lexer.Token;
import compiler.lexer.Tags;

import java.io.IOException;

public class Syntatic {

    private Lexer lexer;
    private Token token;

    public Syntatic(String path) {
        try {
            this.lexer = new Lexer(path);
        } catch (Exception e) {
            System.out.println("Error in Syntatic constructor: " + e.getMessage());
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

    public void error(String errorRule) throws IOException {
        throw new IOException("Syntax error in rule: " + errorRule + "- line " + this.lexer.line);
    }

    public void run() throws IOException {
        this.advance();

        if (this.token.tag == Tags.CLASS) {
            this.program();
            eat(Tags.EOF, "run - EOF expected");
        } else {
            this.error("0");
        }
    }

    public void program() throws IOException {
        try {
            this.eat(Tags.CLASS, "Program");
            this.eat(Tags.ID, "Program");
            this.programL();
        } catch (Exception e) {
            throw e;
        }
    }

    public void programL() throws IOException {
        switch (this.token.tag) {
            case Tags.INT:
            case Tags.LITERAL:
            case Tags.FLOAT:
                this.decList();
                this.body();
                break;
            case Tags.INIT:
                this.body();
                break;
            default:
                this.error("ProgramL");
                break;

        }
    }

    public void decList() throws IOException {
        switch (this.token.tag) {
            case Tags.INT:
            case Tags.LITERAL:
            case Tags.FLOAT:
                this.decl();
                this.eat(Tags.SCOL, "decList");
                this.decListL();
                break;
            default:
                this.error("decList");
                break;
        }
    }

    public void decListL() throws IOException {
        switch (this.token.tag) {
            case Tags.INT:
            case Tags.LITERAL:
            case Tags.FLOAT:
                this.decl();
                this.eat(Tags.SCOL, "decListL");
                this.decListL();
                break;
            case Tags.INIT:
                break;
            default:
                this.error("decListL");
                break;
        }
    }

    public void decl() throws IOException {
        switch (this.token.tag) {
            case Tags.INT:
            case Tags.LITERAL:
            case Tags.FLOAT:
                this.type();
                this.identList();
                break;
            case Tags.SCOL:
                break;
            default:
                this.error("decl");
                break;
        }
    }

    public void type() throws IOException {
        switch (this.token.tag) {
            case Tags.INT:
                this.eat(Tags.INT, "type");
                break;
            case Tags.LITERAL:
                this.eat(Tags.LITERAL, "type");
                break;
            case Tags.FLOAT:
                this.eat(Tags.FLOAT, "type");
                break;
            default:
                this.error("type");
                break;
        }
    }

    public void identList() throws IOException {
        switch (this.token.tag) {
            case Tags.ID:
                this.eat(Tags.ID, "identList");
                this.identListL();
                break;
            default:
                this.error("identList");
                break;
        }
    }

    public void identListL() throws IOException {
        switch (this.token.tag) {
            case Tags.COMMA:
                this.eat(Tags.COMMA, "identListL");
                this.eat(Tags.ID, "identListL");
                this.identListL();
                break;
            case Tags.SCOL:
                break;
            default:
                this.error("identListL");
                break;
        }
    }

    public void body() throws IOException {
        switch (this.token.tag) {
            case Tags.INIT:
                this.eat(Tags.INIT, "body");
                this.stmtList();
                this.eat(Tags.STOP, "body");
                break;

            default:
                this.error("body");
                break;
        }
    }

    public void stmtList() throws IOException {
        switch (this.token.tag) {
            case Tags.ID:
            case Tags.IF:
            case Tags.DO:
            case Tags.READ:
            case Tags.WRITE:
                this.stmt();
                this.eat(Tags.SCOL, "stmtList");
                this.stmtListL();
                break;
            default:
                this.error("stmtList");
                break;
        }
    }

    public void stmtListL() throws IOException {
        switch (this.token.tag) {
            case Tags.ID:
            case Tags.IF:
            case Tags.DO:
            case Tags.READ:
            case Tags.WRITE:
                this.stmt();
                this.eat(Tags.SCOL, "stmtListL");
                this.stmtListL();
                break;
            case Tags.STOP:
            case Tags.RKEY:
                break;
            default:
                this.error("stmtListL");
                break;
        }
    }

    public void stmt() throws IOException {
        switch (this.token.tag) {
            case Tags.ID:
                this.assignStmt();
                break;
            case Tags.IF:
                this.ifStmt();
                break;
            case Tags.DO:
                this.doStmt();
                break;
            case Tags.READ:
                this.readStmt();
                break;
            case Tags.WRITE:
                this.writeStmt();
                break;
            default:
                this.error("stmt");
                break;
        }
    }

    public void assignStmt() throws IOException {
        switch (this.token.tag) {
            case Tags.ID:
                this.eat(Tags.ID, "assignStmt");
                this.eat(Tags.ASSIGN, "assignStmt");
                this.simpleExrp();
                break;
            default:
                this.error("assignStmt");
                break;
        }
    }

    public void ifStmt() throws IOException {
        switch (this.token.tag) {
            case Tags.IF:
                this.eat(Tags.IF, "ifStmt");
                this.eat(Tags.LPAR, "ifStmt");
                this.condition();
                this.eat(Tags.RPAR, "ifStmt");
                this.eat(Tags.LKEY, "ifStmt");
                this.stmtList();
                this.eat(Tags.RKEY, "ifStmt");
                this.ifStmtL();
                break;
            default:
                this.error("ifStmt");
                break;
        }
    }

    public void ifStmtL() throws IOException {
        switch (this.token.tag) {
            case Tags.ELSE:
                this.eat(Tags.ELSE, "ifStmtL");
                this.eat(Tags.LKEY, "ifStmtL");
                this.stmtList();
                this.eat(Tags.RKEY, "ifStmtL");
                break;
            case Tags.SCOL:
                break;
            default:
                break;
        }
    }

    public void doStmt() throws IOException {
        switch (this.token.tag) {
            case Tags.DO:
                this.eat(Tags.DO, "doStmt");
                this.eat(Tags.LKEY, "doStmt");
                this.stmtList();
                this.eat(Tags.RKEY, "doStmt");
                this.doSuffix();
                break;
            default:
                this.error("doStmt");
                break;
        }
    }

    public void doSuffix() throws IOException {
        switch (this.token.tag) {
            case Tags.WHILE:
                this.eat(Tags.WHILE, "doSuffix");
                this.eat(Tags.LPAR, "doSuffix");
                this.condition();
                this.eat(Tags.RPAR, "doSuffix");
                break;
            default:
                this.error("doSuffix");
                break;
        }
    }

    public void readStmt() throws IOException {
        switch (this.token.tag) {
            case Tags.READ:
                this.eat(Tags.READ, "readStmt");
                this.eat(Tags.LPAR, "readStmt");
                this.eat(Tags.ID, "readStmt");
                this.eat(Tags.RPAR, "readStmt");
                break;
            default:
                this.error("readStmt");
                break;
        }
    }

    public void writeStmt() throws IOException {
        switch (this.token.tag) {
            case Tags.WRITE:
                this.eat(Tags.WRITE, "writeStmt");
                this.eat(Tags.LPAR, "writeStmt");
                this.writable();
                this.eat(Tags.RPAR, "writeStmt");
                break;
            default:
                this.error("writeStmt");
                break;
        }
    }

    public void writable() throws IOException {
        switch (this.token.tag) {
            case Tags.EXCL:
            case Tags.SUB:
            case Tags.ID:
            case Tags.LPAR:
            case Tags.INT:
            case Tags.FLOAT:
            case Tags.LITERAL:
                this.simpleExrp();
                break;
            default:
                this.error("writable");
                break;
        }
    }

    public void condition() throws IOException {
        switch (this.token.tag) {
            case Tags.EXCL:
            case Tags.SUB:
            case Tags.ID:
            case Tags.LPAR:
            case Tags.INT:
            case Tags.FLOAT:
            case Tags.LITERAL:
                this.expression();
                break;
            default:
                this.error("condition");
                break;
        }
    }

    public void expression() throws IOException {
        switch (this.token.tag) {
            case Tags.EXCL:
            case Tags.SUB:
            case Tags.ID:
            case Tags.LPAR:
            case Tags.INT:
            case Tags.FLOAT:
            case Tags.LITERAL:
                this.simpleExrp();
                this.expressionL();
                break;
            default:
                this.error("expression");
                break;
        }
    }

    public void simpleExrp() throws IOException {
        switch (this.token.tag) {
            case Tags.EXCL:
            case Tags.SUB:
            case Tags.ID:
            case Tags.LPAR:
            case Tags.INT:
            case Tags.FLOAT:
            case Tags.LITERAL:
                this.term();
                this.simpleExrpL();
                break;
            default:
                this.error("simpleExrp");
                break;
        }
    }

    public void term() throws IOException {
        switch (this.token.tag) {
            case Tags.EXCL:
            case Tags.SUB:
            case Tags.ID:
            case Tags.LPAR:
            case Tags.INT:
            case Tags.FLOAT:
            case Tags.LITERAL:
                this.factorA();
                this.termL();
                break;
            default:
                this.error("term");
                break;
        }
    }

    public void factorA() throws IOException {
        switch (this.token.tag) {
            case Tags.EXCL:
                this.eat(Tags.EXCL, "factorA");
                this.factor();
                break;
            case Tags.SUB:
                this.eat(Tags.SUB, "factorA");
                this.factor();
                break;
            case Tags.ID:
            case Tags.LPAR:
            case Tags.INT:
            case Tags.FLOAT:
            case Tags.LITERAL:
                this.factor();
                break;
            default:
                this.error("factorA");
                break;

        }
    }

    public void factor() throws IOException {
        switch (this.token.tag) {
            case Tags.ID:
                this.eat(Tags.ID, "factor");
                break;
            case Tags.INT:
                this.eat(Tags.INT, "factor");
                break;
            case Tags.FLOAT:
                this.eat(Tags.FLOAT, "factor");
                break;

            case Tags.LITERAL:
                this.eat(Tags.LITERAL, "factor");
                break;
            case Tags.LPAR:
                this.eat(Tags.LPAR, "factor");
                this.expression();
                this.eat(Tags.RPAR, "factor");
                break;

        }
    }

    public void termL() throws IOException {
        switch (this.token.tag) {
            case Tags.MUL:
            case Tags.DIV:
            case Tags.AND:
                this.mulop();
                this.factorA();
                this.termL();
                break;
            case Tags.ADD:
            case Tags.SUB:
            case Tags.OR:
            case Tags.EQ:
            case Tags.DIF:
            case Tags.LE:
            case Tags.LT:
            case Tags.GE:
            case Tags.GT:
            case Tags.RPAR:
            case Tags.SCOL:
                break;
            default:
                this.error("termL");
                break;
        }
    }

    public void simpleExrpL() throws IOException {
        switch (this.token.tag) {
            case Tags.ADD:
            case Tags.SUB:
            case Tags.OR:
                this.addop();
                this.term();
                this.simpleExrpL();
                break;
            case Tags.EQ:
            case Tags.DIF:
            case Tags.LE:
            case Tags.LT:
            case Tags.GE:
            case Tags.GT:
            case Tags.RPAR:
            case Tags.SCOL:
                break;
            default:
                this.error("simpleExrpL");
                break;
        }

    }

    public void expressionL() throws IOException {
        switch (this.token.tag) {
            case Tags.EQ:
            case Tags.DIF:
            case Tags.LE:
            case Tags.LT:
            case Tags.GE:
            case Tags.GT:
                this.relop();
                this.simpleExrp();
                break;
            case Tags.RPAR:
                break;
            default:
                this.error("expressionL");
                break;
        }
    }

    public void relop() throws IOException {
        switch (this.token.tag) {
            case Tags.EQ:
                this.eat(Tags.EQ, "relop");
                break;
            case Tags.DIF:
                this.eat(Tags.DIF, "relop");
                break;
            case Tags.LE:
                this.eat(Tags.LE, "relop");
                break;
            case Tags.LT:
                this.eat(Tags.LT, "relop");
                break;
            case Tags.GE:
                this.eat(Tags.GE, "relop");
                break;
            case Tags.GT:
                this.eat(Tags.GT, "relop");
                break;
            default:
                this.error("relop");
                break;
        }
    }

    public void addop() throws IOException {
        switch (this.token.tag) {
            case Tags.ADD:
                this.eat(Tags.ADD, "addop");
                break;
            case Tags.SUB:
                this.eat(Tags.SUB, "addop");
                break;
            case Tags.OR:
                this.eat(Tags.OR, "addop");
                break;
            default:
                this.error("addop");
                break;
        }
    }

    public void mulop() throws IOException {
        switch (this.token.tag) {
            case Tags.MUL:
                this.eat(Tags.MUL, "mulop");
                break;
            case Tags.DIV:
                this.eat(Tags.DIV, "mulop");
                break;
            case Tags.AND:
                this.eat(Tags.AND, "mulop");
                break;
            default:
                this.error("mulop");
                break;
        }
    }

}
