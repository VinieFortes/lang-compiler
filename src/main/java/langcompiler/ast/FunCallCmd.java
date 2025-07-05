package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

import java.util.List;

public class FunCallCmd extends Cmd {
    private String name;
    private List<Exp> arguments;
    private List<LValue> returnTargets; // Para armazenar os valores de retorno
    
    public FunCallCmd(String name, List<Exp> arguments, List<LValue> returnTargets) {
        this.name = name;
        this.arguments = arguments;
        this.returnTargets = returnTargets;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Exp> getArguments() {
        return arguments;
    }
    
    public List<LValue> getReturnTargets() {
        return returnTargets;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "FunCallCmd{" +
                "name='" + name + '\'' +
                ", arguments=" + arguments +
                ", returnTargets=" + returnTargets +
                '}';
    }
}
