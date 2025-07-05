package langcompiler.ast;
import langcompiler.interpreter.Interpreter;

public class LValueField extends LValue {
    private LValue lvalue;
    private String fieldName;
    
    public LValueField(LValue lvalue, String fieldName) {
        this.lvalue = lvalue;
        this.fieldName = fieldName;
    }
    
    public LValue getLValue() {
        return lvalue;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    @Override 
    public <T> T accept(Interpreter<T> v) { 
        return v.visit(this); 
    }
    
    @Override
    public String toString() {
        return "LValueField{" +
                "lvalue=" + lvalue +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
