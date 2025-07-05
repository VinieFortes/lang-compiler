package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
import java.util.List;
public class Program extends Node {
    public final List<Node> statements;
    public Program(List<Node> statements) { this.statements = statements; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}