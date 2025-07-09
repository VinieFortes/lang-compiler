// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class LValueId extends LValue {
    public final String id;
    public LValueId(String id) { this.id = id; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}