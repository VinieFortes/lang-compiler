package langcompiler.interpreter;

import java.util.HashMap;
import java.util.Map;

public class DataObject {
    private final String typeName;
    private final Map<String, Object> fields;

    public DataObject(String typeName) {
        this.typeName = typeName;
        this.fields = new HashMap<>();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setField(String fieldName, Object value) {
        fields.put(fieldName, value);
    }

    public Object getField(String fieldName) {
        return fields.get(fieldName);
    }

    public boolean hasField(String fieldName) {
        return fields.containsKey(fieldName);
    }

    @Override
    public String toString() {
        return typeName + fields.toString();
    }
}
