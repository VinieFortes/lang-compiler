package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class Parameter extends Node {
    private String name;
    private String type;
    
    public Parameter(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        // Parameter não precisa ser visitado pelo interpretador
        return null; 
    }
    
    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
