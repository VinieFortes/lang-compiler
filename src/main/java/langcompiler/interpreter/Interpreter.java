package langcompiler.interpreter;

import langcompiler.ast.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Interpreter<T> {
    private final Stack<Map<String, Object>> scopes = new Stack<>();
    private final Map<String, FunDef> functions = new HashMap<>();
    private boolean hasReturned = false;
    private Object returnValue = null;

    public Interpreter() {
        scopes.push(new HashMap<>()); // Escopo global
    }

    public void execute(Node node) {
        node.accept(this);
    }

    private void assign(String name, Object value) {
        // Se estamos em uma função (mais de um escopo), cria variável local
        if (scopes.size() > 1) {
            scopes.peek().put(name, value);
            return;
        }
        
        // No escopo global, procura a variável existente primeiro
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                scopes.get(i).put(name, value);
                return;
            }
        }
        // Se não encontrou, declara no escopo atual
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

    public T visit(Program p) {
        // Primeiro, registra todas as funções
        for (Node s : p.statements) {
            if (s instanceof FunDef) {
                FunDef funDef = (FunDef) s;
                functions.put(funDef.getName(), funDef);
            }
        }
        
        // Depois executa comandos (não funções)
        for (Node s : p.statements) {
            if (s != null && !(s instanceof FunDef)) {
                s.accept(this);
            }
        }
        
        // Se existe função main, executa ela sempre que há apenas definições de função
        boolean hasOtherCommands = p.statements.stream()
                .anyMatch(s -> s != null && !(s instanceof FunDef));
        
        if (!hasOtherCommands && functions.containsKey("main")) {
            callFunction("main", new ArrayList<>());
        }
        
        return null;
    }

    public T visit(PrintCmd cmd) {
        System.out.print(cmd.exp.accept(this));
        return null;
    }

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
        } else {
            throw new RuntimeException("Tipo de lvalue não suportado para atribuição");
        }
        
        return null;
    }

    public T visit(Block block) {
        scopes.push(new HashMap<>());
        for(Cmd cmd : block.cmds) {
            cmd.accept(this);
            // Para execução se houver return
            if (hasReturned) {
                break;
            }
        }
        scopes.pop();
        return null;
    }

    public T visit(IfCmd cmd) {
        Object cond = cmd.condition.accept(this);
        if(!(cond instanceof Boolean)) throw new RuntimeException("Condicao do if deve ser booleana");
        if ((Boolean) cond) {
            cmd.thenCmd.accept(this);
        } else if (cmd.elseCmd != null) {
            cmd.elseCmd.accept(this);
        }
        return null;
    }

    public T visit(ReadCmd cmd) {
        // Por enquanto, vamos simular a leitura retornando um valor fixo
        // Em uma implementação completa, isso leria da entrada padrão
        if (cmd.lvalue instanceof LValueId) {
            LValueId lvalueId = (LValueId) cmd.lvalue;
            // Simular leitura - por enquanto atribuir 0
            assign(lvalueId.id, 0);
        }
        return null;
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
            
            // Determinar se é array multidimensional
            String baseType = exp.type;
            int dimensions = 0;
            while (baseType.endsWith("[]")) {
                dimensions++;
                baseType = baseType.substring(0, baseType.length() - 2);
            }
            
            // Criar array com valores padrão
            Object[] array = new Object[size];
            for (int i = 0; i < size; i++) {
                if (dimensions > 1) {
                    // Array multidimensional - inicializa com null
                    array[i] = null;
                } else {
                    // Array unidimensional - inicializa com valores padrão
                    switch (baseType) {
                        case "Int":
                            array[i] = 0;
                            break;
                        case "Float":
                            array[i] = 0.0;
                            break;
                        case "Bool":
                            array[i] = false;
                            break;
                        case "Char":
                            array[i] = '\0';
                            break;
                        default:
                            array[i] = null;
                            break;
                    }
                }
            }
            return (T) array;
        } else {
            // Criação de objeto simples (não implementado ainda)
            throw new RuntimeException("Criação de objetos não implementada");
        }
    }

    public T visit(IterateCmd cmd) {
        Object condValue = cmd.condition.accept(this);
        
        if (condValue instanceof Integer) {
            // Iteração sobre número
            int count = (Integer) condValue;
            
            if (cmd.varName != null) {
                // Iterate com variável de controle: iterate(i : n) executa n vezes com i de 0 a n-1
                for (int i = 0; i < count; i++) {
                    assign(cmd.varName, i);
                    cmd.body.accept(this);
                }
            } else {
                // Iterate simples: iterate(n) executa n vezes
                for (int i = 0; i < count; i++) {
                    cmd.body.accept(this);
                }
            }
        } else if (condValue instanceof Object[]) {
            // Iteração sobre array
            Object[] array = (Object[]) condValue;
            
            if (cmd.varName != null) {
                // Iterate com variável: iterate(i : array) executa para cada elemento
                for (Object element : array) {
                    assign(cmd.varName, element);
                    cmd.body.accept(this);
                }
            } else {
                // Iterate simples sobre array: executa array.length vezes
                for (int i = 0; i < array.length; i++) {
                    cmd.body.accept(this);
                }
            }
        } else {
            throw new RuntimeException("Condição do iterate deve ser um inteiro ou array");
        }
        
        return null;
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

    // MÉTODO ATUALIZADO
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
                if (leftVal instanceof Integer && rightVal instanceof Integer) {
                    int l = (Integer) leftVal;
                    int r = (Integer) rightVal;
                    switch (exp.op) {
                        case "+": return (T) Integer.valueOf(l + r);
                        case "-": return (T) Integer.valueOf(l - r);
                        case "*": return (T) Integer.valueOf(l * r);
                        case "/": return (T) Integer.valueOf(l / r);
                        case "%": return (T) Integer.valueOf(l % r);
                    }
                }
                throw new RuntimeException("Operador aritmetico '" + exp.op + "' exige tipos numericos. Tipos recebidos: " + leftVal.getClass().getSimpleName() + " e " + rightVal.getClass().getSimpleName());

            case "==":
            case "!=":
            case "<":
                // Comparação de Inteiros
                if (leftVal instanceof Integer && rightVal instanceof Integer) {
                    int l = (Integer) leftVal;
                    int r = (Integer) rightVal;
                    switch (exp.op) {
                        case "==": return (T) Boolean.valueOf(l == r);
                        case "!=": return (T) Boolean.valueOf(l != r);
                        case "<":  return (T) Boolean.valueOf(l < r);
                    }
                }
                // NOVO: Comparação de Caracteres
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
            return (T) (Integer) (-(Integer)val);
        }
        if (exp.op.equals("!") && val instanceof Boolean) {
            return (T) (Boolean) (!(Boolean)val);
        }
        throw new RuntimeException("Operador unario invalido para o tipo");
    }

    public T visit(FunDef funDef) {
        // Definições de função são registradas no início, não executadas aqui
        return null;
    }

    public T visit(FunCallCmd cmd) {
        List<Object> results = callFunction(cmd.getName(), cmd.getArguments());
        
        // Atribui valores de retorno aos lvalues especificados
        if (!cmd.getReturnTargets().isEmpty()) {
            for (int i = 0; i < cmd.getReturnTargets().size() && i < results.size(); i++) {
                LValue target = cmd.getReturnTargets().get(i);
                Object value = results.get(i);
                
                if (target instanceof LValueId) {
                    assign(((LValueId) target).id, value);
                } else if (target instanceof LValueArray) {
                    // Implementar atribuição para arrays se necessário
                    throw new RuntimeException("Atribuição a arrays em retorno de função não implementada");
                } else {
                    throw new RuntimeException("Tipo de lvalue não suportado para retorno de função");
                }
            }
        }
        
        return null;
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
        } else {
            // Se não há índice, retorna o primeiro valor (ou null se não há retorno)
            return results.isEmpty() ? null : (T) results.get(0);
        }
    }

    public T visit(ReturnCmd cmd) {
        List<Object> values = new ArrayList<>();
        for (Exp exp : cmd.getExpressions()) {
            values.add(exp.accept(this));
        }
        returnValue = values;
        hasReturned = true;
        return null;
    }

    public T visit(LValueField lvalue) {
        // Não implementado ainda - para tipos personalizados
        throw new RuntimeException("Acesso a campos não implementado");
    }

    @SuppressWarnings("unchecked")
    private List<Object> callFunction(String name, List<Exp> arguments) {
        FunDef funDef = functions.get(name);
        if (funDef == null) {
            throw new RuntimeException("Função '" + name + "' não definida");
        }

        // Avalia argumentos
        List<Object> argValues = new ArrayList<>();
        for (Exp arg : arguments) {
            argValues.add(arg.accept(this));
        }

        // Verifica número de parâmetros
        if (argValues.size() != funDef.getParameters().size()) {
            throw new RuntimeException("Função '" + name + "' espera " + 
                    funDef.getParameters().size() + " argumentos, mas recebeu " + argValues.size());
        }

        // Cria novo escopo para a função
        scopes.push(new HashMap<>());

        // Atribui parâmetros
        for (int i = 0; i < funDef.getParameters().size(); i++) {
            Parameter param = funDef.getParameters().get(i);
            scopes.peek().put(param.getName(), argValues.get(i));
        }

        // Executa corpo da função
        boolean previousHasReturned = hasReturned;  // Salva estado anterior
        Object previousReturnValue = returnValue;   // Salva valor anterior
        hasReturned = false;
        returnValue = null;
        funDef.getBody().accept(this);

        // Processa valor de retorno
        List<Object> results = new ArrayList<>();
        if (returnValue instanceof List) {
            results.addAll((List<Object>) returnValue);
        } else if (returnValue != null) {
            results.add(returnValue);
        }

        // Restaura estado anterior
        hasReturned = previousHasReturned;
        returnValue = previousReturnValue;

        // Remove escopo da função
        scopes.pop();

        return results;
    }
}