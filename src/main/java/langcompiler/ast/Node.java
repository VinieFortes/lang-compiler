package langcompiler.ast;
import langcompiler.interpreter.Interpreter;
public abstract class Node {
    public abstract <T> T accept(Interpreter<T> visitor);
}