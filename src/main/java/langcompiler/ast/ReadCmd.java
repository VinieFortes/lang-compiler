package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class ReadCmd extends Cmd {
    public final LValue lvalue;
    
    public ReadCmd(LValue lvalue) {
        this.lvalue = lvalue;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
}
