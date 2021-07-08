
package compiler.lexer;


public class Num extends Token {
   public final int value;

    public Num(int value) {
        super(Tags.INT);
        this.value = value;
    }

    @Override
    public String toString() {
        return "Num{" + "value = " + value + '}';
    }
  
}
