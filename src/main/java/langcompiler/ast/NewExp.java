package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class NewExp extends Exp {
    public final String type;
    public final Exp size; // para arrays, null para tipos simples
    
    public NewExp(String type, Exp size) {
        this.type = type;
        this.size = size;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
}
