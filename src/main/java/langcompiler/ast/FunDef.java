// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;

import langcompiler.interpreter.Interpreter;

import java.util.List;

public class FunDef extends Node {
    private String name;
    private List<Parameter> parameters;
    private List<String> returnTypes;
    private Cmd body;
    
    public FunDef(String name, List<Parameter> parameters, List<String> returnTypes, Cmd body) {
        this.name = name;
        this.parameters = parameters;
        this.returnTypes = returnTypes;
        this.body = body;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Parameter> getParameters() {
        return parameters;
    }
    
    public List<String> getReturnTypes() {
        return returnTypes;
    }
    
    public Cmd getBody() {
        return body;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "FunDef{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", returnTypes=" + returnTypes +
                ", body=" + body +
                '}';
    }
}
