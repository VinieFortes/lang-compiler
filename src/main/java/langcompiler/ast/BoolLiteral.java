// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class BoolLiteral extends Exp {
    public final boolean value;
    public BoolLiteral(boolean v) { this.value = v; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}