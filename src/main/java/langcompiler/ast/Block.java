// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;

import java.util.List;
import langcompiler.interpreter.Interpreter;

public class Block extends Cmd {
    public final List<Cmd> cmds;
    public Block(List<Cmd> cmds) { this.cmds = cmds; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}