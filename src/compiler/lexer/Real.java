
package compiler.lexer;

public class Real extends Token {
    public final float value;

    public Real(float value) {
        super(Tags.FLOAT);
        this.value = value;
    }

    @Override
    public String toString() {
        return "Real    " + "value = " + value + "  ";
    }

}
