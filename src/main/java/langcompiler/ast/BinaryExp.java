// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public class BinaryExp extends Exp {
    public final Exp left, right;
    public final String op;
    public BinaryExp(Exp l, Exp r, String o) { this.left = l; this.right = r; this.op = o; }
    @Override public <T> T accept(Interpreter<T> v) { return v.visit(this); }
}