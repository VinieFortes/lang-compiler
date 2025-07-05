package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class CharLiteral extends Exp {
    public final char value;
    public CharLiteral(char v) { this.value = v; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}