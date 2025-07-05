package langcompiler.ast;

import langcompiler.interpreter.Interpreter;
import java.util.List;

public class DataDef extends Node {
    private final String name;
    private final List<Parameter> fields;

    public DataDef(String name, List<Parameter> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getFields() {
        return fields;
    }

    @Override
    public <T> T accept(Interpreter<T> visitor) {
        return visitor.visit(this);
    }
}
