package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class IntLiteral extends Exp {
    public final int value;
    public IntLiteral(int v) { this.value = v; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}