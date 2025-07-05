package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class AssignCmd extends Cmd {
    public final LValue lvalue;
    public final Exp exp;
    public AssignCmd(LValue l, Exp e) { this.lvalue = l; this.exp = e; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}