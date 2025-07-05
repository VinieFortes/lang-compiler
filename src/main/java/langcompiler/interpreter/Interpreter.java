package langcompiler.interpreter;

import langcompiler.ast.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter<T> {
    private final Stack<Map<String, Object>> scopes = new Stack<>();

    public Interpreter() {
        scopes.push(new HashMap<>()); // Escopo global
    }

    public void execute(Node node) {
        node.accept(this);
    }

    private void assign(String name, Object value) {
        // Procura a variável nos escopos de dentro para fora para atribuir
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
        for (Node s : p.statements) {
            if(s != null) s.accept(this);
        }
        return null;
    }

    public T visit(PrintCmd cmd) {
        System.out.print(cmd.exp.accept(this));
        return null;
    }

    public T visit(AssignCmd cmd) {
        if (!(cmd.lvalue instanceof LValueId)) throw new RuntimeException("Atribuicao complexa nao suportada");
        String varName = ((LValueId) cmd.lvalue).id;
        Object value = cmd.exp.accept(this);
        assign(varName, value);
        return null;
    }

    public T visit(Block block) {
        scopes.push(new HashMap<>());
        for(Cmd cmd : block.cmds) cmd.accept(this);
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

    @SuppressWarnings("unchecked")
    public T visit(LValueId lvalue) {
        return (T) lookup(lvalue.id);
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
}