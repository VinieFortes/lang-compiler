package langcompiler;

import langcompiler.ast.Node;
import langcompiler.interpreter.Interpreter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Uso: java langcompiler.Main <diretiva> <arquivo.lang>");
            return;
        }
        String directive = args[0];
        String filePath = args[1];

        CharStream input = CharStreams.fromFileName(filePath);
        LangLexer lexer = new LangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LangParser parser = new LangParser(tokens);

        // Remover listeners padrão e adicionar custom para lexer e parser
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        CustomErrorListener errorListener = new CustomErrorListener();
        lexer.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.prog();

        if (errorListener.hasErrors()) {
            if ("-syn".equals(directive)) System.out.println("reject");
            else System.err.println("Programa com erros de sintaxe. Abortando.");
            return;
        }
        if ("-syn".equals(directive)) {
            System.out.println("accept");
            return;
        }
        if ("-i".equals(directive)) {
            AstBuilder astBuilder = new AstBuilder();
            Node ast = astBuilder.visit(tree);
            Interpreter interpreter = new Interpreter();
            try {
                interpreter.execute(ast);
            } catch (Exception e) {
                System.err.println("Erro de execucao: " + e.getMessage());
            }
        } else {
            System.err.println("Diretiva desconhecida: " + directive);
        }
    }
}

class CustomErrorListener extends BaseErrorListener {
    private boolean hasErrors = false;
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        hasErrors = true;
    }
    public boolean hasErrors() {
        return hasErrors;
    }
}