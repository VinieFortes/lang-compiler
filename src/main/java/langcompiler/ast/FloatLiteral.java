package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class FloatLiteral extends Exp {
    public final double value;
    
    public FloatLiteral(double value) {
        this.value = value;
    }
    
    public double getValue() {
        return value;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "FloatLiteral{" +
                "value=" + value +
                '}';
    }
}
