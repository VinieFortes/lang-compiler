package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class IfCmd extends Cmd {
    public final Exp condition;
    public final Cmd thenCmd, elseCmd;
    public IfCmd(Exp c, Cmd t, Cmd e) { this.condition = c; this.thenCmd = t; this.elseCmd = e; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}