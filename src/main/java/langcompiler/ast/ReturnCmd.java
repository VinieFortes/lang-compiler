// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;

import langcompiler.interpreter.Interpreter;

import java.util.List;

public class ReturnCmd extends Cmd {
    private List<Exp> expressions;
    
    public ReturnCmd(List<Exp> expressions) {
        this.expressions = expressions;
    }
    
    public List<Exp> getExpressions() {
        return expressions;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "ReturnCmd{" +
                "expressions=" + expressions +
                '}';
    }
}
