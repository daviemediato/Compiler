package compiler.lexer;

public class Literal extends Token {
    public final String value;

    public Literal(String value) {
        super(Tags.LITERAL);
        this.value = value;
    }

    @Override
    public String toString() {
        return "Literal     " + "value = " + value + "  ";
    }

}