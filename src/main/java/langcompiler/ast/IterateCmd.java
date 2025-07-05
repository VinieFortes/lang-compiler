package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class IterateCmd extends Cmd {
    public final String varName;  // Pode ser null se não há variável de controle
    public final Exp condition;
    public final Cmd body;
    
    public IterateCmd(String varName, Exp condition, Cmd body) {
        this.varName = varName;
        this.condition = condition;
        this.body = body;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
}
