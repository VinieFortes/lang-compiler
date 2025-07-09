// Vinicius da Silva Fortes
// Matricula 201935029
package langcompiler;

import langcompiler.ast.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class AstBuilder extends LangBaseVisitor<Node> {

    @Override
    public Node visitProg(LangParser.ProgContext ctx) {
        return new Program(
                ctx.children.stream()
                        .map(this::visit)
                        .filter(java.util.Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Node visitCmdPrint(LangParser.CmdPrintContext ctx) {
        return new PrintCmd((Exp) visit(ctx.exp()));
    }

    @Override
    public Node visitCmdAssign(LangParser.CmdAssignContext ctx) {
        return new AssignCmd((LValue) visit(ctx.lvalue()), (Exp) visit(ctx.exp()));
    }

    @Override
    public Node visitLValueId(LangParser.LValueIdContext ctx) {
        return new LValueId(ctx.ID().getText());
    }

    @Override
    public Node visitLValueArray(LangParser.LValueArrayContext ctx) {
        LValue lvalue = (LValue) visit(ctx.lvalue());
        Exp index = (Exp) visit(ctx.exp());
        return new LValueArray(lvalue, index);
    }

    @Override
    public Node visitLValueField(LangParser.LValueFieldContext ctx) {
        LValue lvalue = (LValue) visit(ctx.lvalue());
        String fieldName = ctx.ID().getText();
        return new LValueField(lvalue, fieldName);
    }

    @Override
    public Node visitPrimaryExpLiteral(LangParser.PrimaryExpLiteralContext ctx) {
        if (ctx.literal().INT() != null) {
            return new IntLiteral(Integer.parseInt(ctx.literal().INT().getText()));
        }
        if (ctx.literal().FLOAT() != null) {
            return new FloatLiteral(Double.parseDouble(ctx.literal().FLOAT().getText()));
        }
        if (ctx.literal().TRUE() != null) {
            return new BoolLiteral(true);
        }
        if (ctx.literal().FALSE() != null) {
            return new BoolLiteral(false);
        }
        if (ctx.literal().NULL() != null) {
            return new NullLiteral();
        }
        if (ctx.literal().CHAR() != null) {
            String text = ctx.literal().CHAR().getText();
            String unquoted = text.substring(1, text.length() - 1); // Remove as aspas

            if (unquoted.startsWith("\\")) {
                // É uma sequência de escape
                String escape = unquoted.substring(1);
                switch (escape) {
                    case "n": return new CharLiteral('\n');
                    case "t": return new CharLiteral('\t');
                    case "b": return new CharLiteral('\b');
                    case "r": return new CharLiteral('\r');
                    case "'": return new CharLiteral('\'');
                    case "\\": return new CharLiteral('\\');
                    default:
                        // Verifica se é um código ASCII \ddd
                        if (escape.matches("[0-9]{3}")) {
                            int asciiCode = Integer.parseInt(escape);
                            return new CharLiteral((char) asciiCode);
                        }
                        throw new RuntimeException("Sequencia de escape invalida: " + unquoted);
                }
            } else {
                // É um caractere normal
                return new CharLiteral(unquoted.charAt(0));
            }
        }
        return null;
    }

    @Override
    public Node visitPrimaryExpLValue(LangParser.PrimaryExpLValueContext ctx) {
        return visit(ctx.lvalue());
    }

    @Override
    public Node visitExpOp(LangParser.ExpOpContext ctx) {
        return new BinaryExp((Exp) visit(ctx.exp(0)), (Exp) visit(ctx.exp(1)), ctx.getChild(1).getText());
    }

    @Override
    public Node visitUnaryExpOp(LangParser.UnaryExpOpContext ctx) {
        return new UnaryExp((Exp) visit(ctx.unaryExp()), ctx.getChild(0).getText());
    }

    @Override
    public Node visitPrimaryExpParen(LangParser.PrimaryExpParenContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public Node visitExpEquality(LangParser.ExpEqualityContext ctx) {
        return visit(ctx.equalityExp());
    }

    @Override
    public Node visitEqualityExpOp(LangParser.EqualityExpOpContext ctx) {
        return new BinaryExp((Exp) visit(ctx.equalityExp(0)), (Exp) visit(ctx.equalityExp(1)), ctx.getChild(1).getText());
    }

    @Override
    public Node visitEqualityExpRelational(LangParser.EqualityExpRelationalContext ctx) {
        return visit(ctx.relationalExp());
    }

    @Override
    public Node visitRelationalExpOp(LangParser.RelationalExpOpContext ctx) {
        return new BinaryExp((Exp) visit(ctx.additiveExp(0)), (Exp) visit(ctx.additiveExp(1)), ctx.getChild(1).getText());
    }

    @Override
    public Node visitRelationalExpAdditive(LangParser.RelationalExpAdditiveContext ctx) {
        return visit(ctx.additiveExp());
    }

    @Override
    public Node visitAdditiveExpOp(LangParser.AdditiveExpOpContext ctx) {
        return new BinaryExp((Exp) visit(ctx.additiveExp(0)), (Exp) visit(ctx.additiveExp(1)), ctx.getChild(1).getText());
    }

    @Override
    public Node visitAdditiveExpMultiplicative(LangParser.AdditiveExpMultiplicativeContext ctx) {
        return visit(ctx.multiplicativeExp());
    }

    @Override
    public Node visitMultiplicativeExpOp(LangParser.MultiplicativeExpOpContext ctx) {
        return new BinaryExp((Exp) visit(ctx.multiplicativeExp(0)), (Exp) visit(ctx.multiplicativeExp(1)), ctx.getChild(1).getText());
    }

    @Override
    public Node visitMultiplicativeExpUnary(LangParser.MultiplicativeExpUnaryContext ctx) {
        return visit(ctx.unaryExp());
    }

    @Override
    public Node visitUnaryExpPrimary(LangParser.UnaryExpPrimaryContext ctx) {
        return visit(ctx.primaryExp());
    }

    @Override
    public Node visitCmdFunCall(LangParser.CmdFunCallContext ctx) {
        String name = ctx.ID().getText();
        List<Exp> arguments = new ArrayList<>();
        if (ctx.exps() != null) {
            arguments = ctx.exps().exp().stream()
                    .map(expCtx -> (Exp) visit(expCtx))
                    .collect(Collectors.toList());
        }
        
        List<LValue> returnTargets = new ArrayList<>();
        if (ctx.lvalue() != null) {
            returnTargets = ctx.lvalue().stream()
                    .map(lvalCtx -> (LValue) visit(lvalCtx))
                    .collect(Collectors.toList());
        }
        
        return new FunCallCmd(name, arguments, returnTargets);
    }

    @Override
    public Node visitCmdIf(LangParser.CmdIfContext ctx) {
        Exp condition = (Exp) visit(ctx.exp());
        Cmd thenCmd = (Cmd) visit(ctx.cmd(0));
        Cmd elseCmd = (ctx.ELSE() != null) ? (Cmd) visit(ctx.cmd(1)) : null;
        return new IfCmd(condition, thenCmd, elseCmd);
    }

    @Override
    public Node visitBlock(LangParser.BlockContext ctx) {
        return new Block(
                ctx.cmd().stream()
                        .map(cmdCtx -> (Cmd) visit(cmdCtx))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Node visitCmdBlock(LangParser.CmdBlockContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public Node visitCmdRead(LangParser.CmdReadContext ctx) {
        LValue lvalue = (LValue) visit(ctx.lvalue());
        return new ReadCmd(lvalue);
    }

    @Override
    public Node visitCmdIterate(LangParser.CmdIterateContext ctx) {
        LangParser.ItcondContext itcondCtx = ctx.itcond();
        String varName = null;
        Exp condition = null;
        
        if (itcondCtx.ID() != null) {
            // Caso ID ':' exp
            varName = itcondCtx.ID().getText();
            condition = (Exp) visit(itcondCtx.exp());
        } else {
            // Caso apenas exp
            condition = (Exp) visit(itcondCtx.exp());
        }
        
        Cmd body = (Cmd) visit(ctx.cmd());
        return new IterateCmd(varName, condition, body);
    }

    @Override
    public Node visitPrimaryExpNew(LangParser.PrimaryExpNewContext ctx) {
        String type = ctx.type().getText();
        Exp size = null;
        if (ctx.exp() != null) {
            size = (Exp) visit(ctx.exp());
        }
        return new NewExp(type, size);
    }

    @Override
    public Node visitFun(LangParser.FunContext ctx) {
        String name = ctx.ID().getText();
        
        List<Parameter> parameters = new ArrayList<>();
        if (ctx.params() != null) {
            for (int i = 0; i < ctx.params().ID().size(); i++) {
                String paramName = ctx.params().ID(i).getText();
                String paramType = ctx.params().type(i).getText();
                parameters.add(new Parameter(paramName, paramType));
            }
        }
        
        List<String> returnTypes = new ArrayList<>();
        if (ctx.type() != null) {
            returnTypes = ctx.type().stream()
                    .map(typeCtx -> typeCtx.getText())
                    .collect(Collectors.toList());
        }
        
        Cmd body = (Cmd) visit(ctx.cmd());
        return new FunDef(name, parameters, returnTypes, body);
    }

    @Override
    public Node visitCmdReturn(LangParser.CmdReturnContext ctx) {
        List<Exp> expressions = ctx.exp().stream()
                .map(expCtx -> (Exp) visit(expCtx))
                .collect(Collectors.toList());
        return new ReturnCmd(expressions);
    }

    @Override
    public Node visitPrimaryExpFunCallReturn(LangParser.PrimaryExpFunCallReturnContext ctx) {
        String name = ctx.ID().getText();
        List<Exp> arguments = new ArrayList<>();
        if (ctx.exps() != null) {
            arguments = ctx.exps().exp().stream()
                    .map(expCtx -> (Exp) visit(expCtx))
                    .collect(Collectors.toList());
        }
        Exp index = (Exp) visit(ctx.exp());
        return new FunCallExp(name, arguments, index);
    }

    @Override
    public Node visitData(LangParser.DataContext ctx) {
        String typeName = ctx.TYID().getText();
        List<Parameter> fields = new ArrayList<>();
        
        if (ctx.decl() != null) {
            for (LangParser.DeclContext declCtx : ctx.decl()) {
                String fieldName = declCtx.ID().getText();
                String fieldType = declCtx.type().getText();
                fields.add(new Parameter(fieldName, fieldType));
            }
        }
        
        return new DataDef(typeName, fields);
    }
}