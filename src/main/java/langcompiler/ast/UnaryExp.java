// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class UnaryExp extends Exp {
    public final Exp exp;
    public final String op;
    public UnaryExp(Exp e, String o) { this.exp = e; this.op = o; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}