package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class LValueArray extends LValue {
    public final LValue lvalue;
    public final Exp index;
    
    public LValueArray(LValue lvalue, Exp index) {
        this.lvalue = lvalue;
        this.index = index;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
}
