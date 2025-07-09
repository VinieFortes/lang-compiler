// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler.ast;

import langcompiler.LangBaseVisitor;
import langcompiler.interpreter.Interpreter;

public abstract class Node {
    public abstract <T> T accept(Interpreter<T> visitor);
}