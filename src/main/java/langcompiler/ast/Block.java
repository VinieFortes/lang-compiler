package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
import java.util.List;
public class Block extends Cmd {
    public final List<Cmd> cmds;
    public Block(List<Cmd> cmds) { this.cmds = cmds; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}