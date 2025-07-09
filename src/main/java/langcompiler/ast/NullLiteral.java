// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class NullLiteral extends Exp {
    
    public NullLiteral() {
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "NullLiteral{}";
    }
}
