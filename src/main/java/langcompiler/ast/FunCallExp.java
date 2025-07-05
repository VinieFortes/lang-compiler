package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

import java.util.List;

public class FunCallExp extends Exp {
    private String name;
    private List<Exp> arguments;
    private Exp index; // Para acessar o valor de retorno específico com [index]
    
    public FunCallExp(String name, List<Exp> arguments, Exp index) {
        this.name = name;
        this.arguments = arguments;
        this.index = index;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Exp> getArguments() {
        return arguments;
    }
    
    public Exp getIndex() {
        return index;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "FunCallExp{" +
                "name='" + name + '\'' +
                ", arguments=" + arguments +
                ", index=" + index +
                '}';
    }
}
