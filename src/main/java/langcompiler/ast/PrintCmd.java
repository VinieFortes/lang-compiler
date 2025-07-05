package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class PrintCmd extends Cmd {
    public final Exp exp;
    public PrintCmd(Exp e) { this.exp = e; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}