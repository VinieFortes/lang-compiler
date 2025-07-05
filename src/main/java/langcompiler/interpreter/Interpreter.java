package langcompiler.interpreter;

import langcompiler.ast.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

// Classe auxiliar para encapsular o resultado de uma execução
class ExecutionResult<T> {
    final boolean shouldReturn;
    final T value;

    ExecutionResult(boolean shouldReturn, T value) {
        this.shouldReturn = shouldReturn;
        this.value = value;
    }

    static <T> ExecutionResult<T> forValue(T value) {
        return new ExecutionResult<>(false, value);
    }

    static <T> ExecutionResult<T> forReturn(T returnValue) {
        return new ExecutionResult<>(true, returnValue);
    }
}

public class Interpreter<T> {
    private final Stack<Map<String, Object>> scopes = new Stack<>();
    private final Map<String, FunDef> functions = new HashMap<>();
    private final Map<String, DataDef> dataTypes = new HashMap<>();

    public Interpreter() {
        scopes.push(new HashMap<>()); // Escopo global
    }

    public void execute(Node node) {
        node.accept(this);
    }

    private void assign(String name, Object value) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                scopes.get(i).put(name, value);
                return;
            }
        }
        scopes.peek().put(name, value);
    }

    private Object lookup(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                return scopes.get(i).get(name);
            }
        }
        throw new RuntimeException("Variavel '" + name + "' nao definida.");
    }

    @SuppressWarnings("unchecked")
    public T visit(Program p) {
        // 1. Primeiro, registra todas as definições de tipos de dados e funções no programa.
        for (Node s : p.statements) {
            if (s instanceof DataDef) {
                DataDef dataDef = (DataDef) s;
                dataTypes.put(dataDef.getName(), dataDef);
            } else if (s instanceof FunDef) {
                FunDef funDef = (FunDef) s;
                functions.put(funDef.getName(), funDef);
            }
        }

        // 2. A especificação da linguagem define que a execução de um programa
        //    completo se dá pela avaliação da sua função 'main'.
        FunDef mainFunction = functions.get("main");

        if (mainFunction != null) {
            // Verifica se a função 'main' não tem parâmetros, como é o padrão.
            if (mainFunction.getParameters() == null || mainFunction.getParameters().isEmpty()) {
                // A execução começa e termina aqui.
                callFunction("main", new ArrayList<>());
            } else {
                throw new RuntimeException("A funcao 'main' nao deve ter parametros.");
            }
        } else {
            // Um programa bem formado precisa de uma função 'main'.
            throw new RuntimeException("Ponto de entrada 'main()' nao encontrado. A execucao nao pode comecar.");
        }

        // Nenhum outro comando de nível superior deve ser executado.
        return null;
    }

    @SuppressWarnings("unchecked")
    public T visit(PrintCmd cmd) {
        Object value = cmd.exp.accept(this);
        if (value instanceof Double) {
            // Formata o Double para 7 casas decimais, usando PONTO como separador
            System.out.print(String.format(Locale.US, "%.7f", (Double) value));
        } else {
            System.out.print(value);
        }
        return (T) ExecutionResult.forValue(null);
    }

    @SuppressWarnings("unchecked")
    public T visit(AssignCmd cmd) {
        Object value = cmd.exp.accept(this);
        
        if (cmd.lvalue instanceof LValueId) {
            String varName = ((LValueId) cmd.lvalue).id;
            assign(varName, value);
        } else if (cmd.lvalue instanceof LValueArray) {
            LValueArray arrayLValue = (LValueArray) cmd.lvalue;
            Object arrayObj = arrayLValue.lvalue.accept(this);
            Object indexObj = arrayLValue.index.accept(this);
            
            if (!(arrayObj instanceof Object[])) {
                throw new RuntimeException("Tentativa de atribuição a array em objeto que não é array");
            }
            if (!(indexObj instanceof Integer)) {
                throw new RuntimeException("Índice do array deve ser inteiro");
            }
            
            Object[] array = (Object[]) arrayObj;
            int index = (Integer) indexObj;
            
            if (index < 0 || index >= array.length) {
                throw new RuntimeException("Índice fora dos limites do array");
            }
            
            array[index] = value;
        } else if (cmd.lvalue instanceof LValueField) {
            LValueField fieldLValue = (LValueField) cmd.lvalue;
            Object obj = fieldLValue.getLValue().accept(this);
            
            if (!(obj instanceof DataObject)) {
                throw new RuntimeException("Tentativa de atribuição a campo em objeto que não é do tipo definido pelo usuário");
            }
            
            DataObject dataObj = (DataObject) obj;
            dataObj.setField(fieldLValue.getFieldName(), value);
        } else {
            throw new RuntimeException("Tipo de lvalue não suportado para atribuição");
        }
        
        return (T) ExecutionResult.forValue(null);
    }

    @SuppressWarnings("unchecked")
    public T visit(Block block) {
        scopes.push(new HashMap<>());
        for (Cmd cmd : block.cmds) {
            ExecutionResult<T> result = (ExecutionResult<T>) cmd.accept(this);
            if (result.shouldReturn) {
                scopes.pop();
                return (T) result;
            }
        }
        scopes.pop();
        return (T) ExecutionResult.forValue(null);
    }

    @SuppressWarnings("unchecked")
    public T visit(IfCmd cmd) {
        Object cond = cmd.condition.accept(this);
        if(!(cond instanceof Boolean)) throw new RuntimeException("Condicao do if deve ser booleana");
        
        if ((Boolean) cond) {
            return (T) cmd.thenCmd.accept(this);
        } else if (cmd.elseCmd != null) {
            return (T) cmd.elseCmd.accept(this);
        }
        
        return (T) ExecutionResult.forValue(null);
    }

    @SuppressWarnings("unchecked")
    public T visit(ReadCmd cmd) {
        if (cmd.lvalue instanceof LValueId) {
            LValueId lvalueId = (LValueId) cmd.lvalue;
            try {
                // Read actual input from System.in
                int value = System.in.read();
                while (Character.isWhitespace(value)) {
                    value = System.in.read();
                }
                StringBuilder number = new StringBuilder();
                while (!Character.isWhitespace(value) && value != -1) {
                    number.append((char) value);
                    value = System.in.read();
                }
                assign(lvalueId.id, Integer.parseInt(number.toString()));
            } catch (Exception e) {
                throw new RuntimeException("Erro ao ler entrada: " + e.getMessage());
            }
        }
        return (T) ExecutionResult.forValue(null);
    }

    @SuppressWarnings("unchecked")
    public T visit(NewExp exp) {
        if (exp.size != null) {
            // Criação de array
            Object sizeValue = exp.size.accept(this);
            if (!(sizeValue instanceof Integer)) {
                throw new RuntimeException("Tamanho do array deve ser inteiro");
            }
            int size = (Integer) sizeValue;
            
            // Contar quantas dimensões tem o array
            String type = exp.type;
            int arrayDimensions = 0;
            String baseType = type;
            while (baseType.endsWith("[]")) {
                arrayDimensions++;
                baseType = baseType.substring(0, baseType.length() - 2);
            }
            
            System.err.println("DEBUG: Criando array do tipo '" + type + "' com " + arrayDimensions + " dimensões, baseType='" + baseType + "'");
            
            // Se tem exp.size, então sempre é um array, independente do tipo
            // O tipo diz o que vai dentro do array
            if (arrayDimensions == 0) {
                // Tipo base com [size] - criar array do tipo base
                return (T) new Object[size];
            } else if (arrayDimensions == 1) {
                // Tipo[] com [size] - criar array bidimensional
                return (T) new Object[size][];
            } else if (arrayDimensions == 2) {
                // Tipo[][] com [size] - criar array tridimensional
                return (T) new Object[size][][];
            } else {
                System.err.println("AVISO: Arrays com " + (arrayDimensions + 1) + " dimensões podem não funcionar corretamente");
                return (T) new Object[size];
            }
        } else {
            // Criação de objeto de tipo definido pelo usuário
            String typeName = exp.type;
            if (dataTypes.containsKey(typeName)) {
                return (T) new DataObject(typeName);
            } else if (isPrimitiveType(typeName)) {
                // Para tipos primitivos, retorna um valor padrão
                switch (typeName) {
                    case "Int": return (T) Integer.valueOf(0);
                    case "Float": return (T) Double.valueOf(0.0);
                    case "Bool": return (T) Boolean.valueOf(false);
                    case "Char": return (T) Character.valueOf('\0');
                    default:
                        throw new RuntimeException("Tipo primitivo desconhecido: " + typeName);
                }
            } else {
                throw new RuntimeException("Tipo não definido: " + typeName);
            }
        }
    }

    private boolean isPrimitiveType(String type) {
        return "Int".equals(type) || "Float".equals(type) || "Bool".equals(type) || "Char".equals(type);
    }

    @SuppressWarnings("unchecked")
    public T visit(IterateCmd cmd) {
        Object condValue = cmd.condition.accept(this);
        
        if (condValue instanceof Integer) {
            for (int i = 0; i < (Integer) condValue; i++) {
                if (cmd.varName != null) assign(cmd.varName, i);
                ExecutionResult<T> result = (ExecutionResult<T>) cmd.body.accept(this);
                if (result.shouldReturn) return (T) result;
            }
        } else if (condValue instanceof Object[]) {
            for (Object element : (Object[]) condValue) {
                if (cmd.varName != null) assign(cmd.varName, element);
                ExecutionResult<T> result = (ExecutionResult<T>) cmd.body.accept(this);
                if (result.shouldReturn) return (T) result;
            }
        } else {
            throw new RuntimeException("Condição do iterate deve ser um inteiro ou array");
        }
        
        return (T) ExecutionResult.forValue(null);
    }

    @SuppressWarnings("unchecked")
    public T visit(LValueId lvalue) {
        return (T) lookup(lvalue.id);
    }
    
    @SuppressWarnings("unchecked")
    public T visit(LValueArray lvalue) {
        Object arrayObj = lvalue.lvalue.accept(this);
        Object indexObj = lvalue.index.accept(this);
        
        if (!(arrayObj instanceof Object[])) {
            throw new RuntimeException("Tentativa de acesso a array em objeto que não é array");
        }
        if (!(indexObj instanceof Integer)) {
            throw new RuntimeException("Índice do array deve ser inteiro");
        }
        
        Object[] array = (Object[]) arrayObj;
        int index = (Integer) indexObj;
        
        if (index < 0 || index >= array.length) {
            throw new RuntimeException("Índice fora dos limites do array");
        }
        
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T visit(IntLiteral literal) {
        return (T) (Integer) literal.value;
    }

    @SuppressWarnings("unchecked")
    public T visit(CharLiteral literal) {
        return (T) (Character) literal.value;
    }

    @SuppressWarnings("unchecked")
    public T visit(BoolLiteral literal) {
        return (T) (Boolean) literal.value;
    }

    @SuppressWarnings("unchecked")
    public T visit(FloatLiteral literal) {
        return (T) (Double) literal.getValue();
    }

    @SuppressWarnings("unchecked")
    public T visit(NullLiteral literal) {
        return (T) null;
    }

    @SuppressWarnings("unchecked")
    public T visit(BinaryExp exp) {
        Object leftVal = exp.left.accept(this);
        Object rightVal = exp.right.accept(this);

        switch (exp.op) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                // Lógica para quando ambos são numéricos (Int ou Double)
                if (leftVal instanceof Number && rightVal instanceof Number) {
                    double l = ((Number) leftVal).doubleValue();
                    double r = ((Number) rightVal).doubleValue();

                    // Se a operação for inteira e os tipos originais também, mantenha o resultado como inteiro
                    boolean isIntegerOperation = (leftVal instanceof Integer && rightVal instanceof Integer);

                    switch (exp.op) {
                        case "+":
                            return isIntegerOperation ? (T) Integer.valueOf((int) (l + r)) : (T) Double.valueOf(l + r);
                        case "-":
                            return isIntegerOperation ? (T) Integer.valueOf((int) (l - r)) : (T) Double.valueOf(l - r);
                        case "*":
                            return isIntegerOperation ? (T) Integer.valueOf((int) (l * r)) : (T) Double.valueOf(l * r);
                        case "/":
                            // Divisão sempre resulta em Double se um dos operandos for Double
                            if (!isIntegerOperation) {
                                return (T) Double.valueOf(l / r);
                            }
                            // Divisão inteira
                            if (r == 0) throw new RuntimeException("Divisão por zero.");
                            return (T) Integer.valueOf((int)l / (int)r);
                        case "%":
                            if (isIntegerOperation) {
                                return (T) Integer.valueOf((int)l % (int)r);
                            } else {
                                throw new RuntimeException("Operador '%' so eh valido para inteiros.");
                            }
                    }
                }
                throw new RuntimeException("Operador aritmetico '" + exp.op + "' exige tipos numericos. Tipos recebidos: " + leftVal.getClass().getSimpleName() + " e " + rightVal.getClass().getSimpleName());

            case "==":
            case "!=":
            case "<":
                // Comparação de Doubles/Floats
                if (leftVal instanceof Number && rightVal instanceof Number) {
                    double l = ((Number) leftVal).doubleValue();
                    double r = ((Number) rightVal).doubleValue();
                    switch (exp.op) {
                        case "==": return (T) Boolean.valueOf(l == r);
                        case "!=": return (T) Boolean.valueOf(l != r);
                        case "<":  return (T) Boolean.valueOf(l < r);
                    }
                }
                // Comparação de Caracteres
                if (leftVal instanceof Character && rightVal instanceof Character) {
                    char l = (Character) leftVal;
                    char r = (Character) rightVal;
                    switch (exp.op) {
                        case "==": return (T) Boolean.valueOf(l == r);
                        case "!=": return (T) Boolean.valueOf(l != r);
                        case "<":  return (T) Boolean.valueOf(l < r);
                    }
                }
                throw new RuntimeException("Operador de comparacao '" + exp.op + "' nao suportado para os tipos: " + leftVal.getClass().getSimpleName() + " e " + rightVal.getClass().getSimpleName());

            case "&&":
                if (leftVal instanceof Boolean && rightVal instanceof Boolean) {
                    return (T) Boolean.valueOf((Boolean) leftVal && (Boolean) rightVal);
                }
                throw new RuntimeException("Operador logico '&&' exige tipos booleanos.");

            default:
                throw new RuntimeException("Operador binario desconhecido: " + exp.op);
        }
    }

    @SuppressWarnings("unchecked")
    public T visit(UnaryExp exp) {
        Object val = exp.exp.accept(this);
        if (exp.op.equals("-") && val instanceof Integer) {
            return (T) Integer.valueOf(-(Integer)val);
        }
        if (exp.op.equals("!") && val instanceof Boolean) {
            return (T) Boolean.valueOf(!(Boolean)val);
        }
        throw new RuntimeException("Operador unário inválido para o tipo");
    }

    @SuppressWarnings("unchecked")
    public T visit(FunDef funDef) {
        return (T) ExecutionResult.forValue(null);
    }
    
    @SuppressWarnings("unchecked")
    public T visit(FunCallCmd cmd) {
        List<Object> results = callFunction(cmd.getName(), cmd.getArguments());
        
        if (!cmd.getReturnTargets().isEmpty()) {
            for (int i = 0; i < cmd.getReturnTargets().size() && i < results.size(); i++) {
                LValue target = cmd.getReturnTargets().get(i);
                Object value = results.get(i);
                
                if (target instanceof LValueId) {
                    assign(((LValueId) target).id, value);
                } else {
                    throw new RuntimeException("Tipo de lvalue não suportado para retorno de função");
                }
            }
        }
        
        return (T) ExecutionResult.forValue(null);
    }
    
    @SuppressWarnings("unchecked")
    public T visit(FunCallExp exp) {
        List<Object> results = callFunction(exp.getName(), exp.getArguments());
        
        if (exp.getIndex() != null) {
            Object indexObj = exp.getIndex().accept(this);
            if (!(indexObj instanceof Integer)) {
                throw new RuntimeException("Índice de retorno deve ser inteiro");
            }
            int index = (Integer) indexObj;
            
            if (index < 0 || index >= results.size()) {
                throw new RuntimeException("Índice de retorno fora dos limites");
            }
            
            return (T) results.get(index);
        }
        
        return results.isEmpty() ? null : (T) results.get(0);
    }
    
    @SuppressWarnings("unchecked")
    public T visit(ReturnCmd cmd) {
        List<Object> values = new ArrayList<>();
        for (Exp exp : cmd.getExpressions()) {
            values.add(exp.accept(this));
        }
        return (T) ExecutionResult.forReturn(values);
    }

    @SuppressWarnings("unchecked")
    public T visit(LValueField lvalue) {
        Object obj = lvalue.getLValue().accept(this);
        if (!(obj instanceof DataObject)) {
            throw new RuntimeException("Tentativa de acessar campo em objeto que não é do tipo definido pelo usuário");
        }
        DataObject dataObj = (DataObject) obj;
        return (T) dataObj.getField(lvalue.getFieldName());
    }

    @SuppressWarnings("unchecked")
    public T visit(DataDef dataDef) {
        // Para definições de tipos de dados, simplesmente registramos o tipo
        // O tipo será usado quando criamos objetos com 'new'
        return null;
    }

    @SuppressWarnings("unchecked")
    private List<Object> callFunction(String name, List<Exp> arguments) {
        FunDef funDef = functions.get(name);
        if (funDef == null) {
            throw new RuntimeException("Função '" + name + "' não definida");
        }

        List<Object> argValues = new ArrayList<>();
        if (arguments != null) {
            for (Exp arg : arguments) {
                argValues.add(arg.accept(this));
            }
        }

        if (argValues.size() != funDef.getParameters().size()) {
            throw new RuntimeException("Função '" + name + "' espera " + 
                    funDef.getParameters().size() + " argumentos, mas recebeu " + argValues.size());
        }

        scopes.push(new HashMap<>());
        for (int i = 0; i < funDef.getParameters().size(); i++) {
            scopes.peek().put(funDef.getParameters().get(i).getName(), argValues.get(i));
        }

        ExecutionResult<T> result = (ExecutionResult<T>) funDef.getBody().accept(this);

        scopes.pop();

        if (result.shouldReturn && result.value instanceof List) {
            return (List<Object>) result.value;
        }

        return Collections.emptyList();
    }
}
