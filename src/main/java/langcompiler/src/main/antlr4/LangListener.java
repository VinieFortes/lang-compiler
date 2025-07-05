// Generated from src/main/antlr4/Lang.g4 by ANTLR 4.13.2
package langcompiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(LangParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(LangParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(LangParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(LangParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(LangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(LangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(LangParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(LangParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(LangParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(LangParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterBtype(LangParser.BtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitBtype(LangParser.BtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdBlock}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdBlock(LangParser.CmdBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdBlock}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdBlock(LangParser.CmdBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdIf}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdIf(LangParser.CmdIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdIf}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdIf(LangParser.CmdIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdIterate}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdIterate(LangParser.CmdIterateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdIterate}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdIterate(LangParser.CmdIterateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdRead}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdRead(LangParser.CmdReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdRead}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdRead(LangParser.CmdReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdPrint}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdPrint(LangParser.CmdPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdPrint}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdPrint(LangParser.CmdPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdReturn}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdReturn(LangParser.CmdReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdReturn}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdReturn(LangParser.CmdReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdAssign}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdAssign(LangParser.CmdAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdAssign}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdAssign(LangParser.CmdAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmdFunCall}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmdFunCall(LangParser.CmdFunCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmdFunCall}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmdFunCall(LangParser.CmdFunCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#itcond}.
	 * @param ctx the parse tree
	 */
	void enterItcond(LangParser.ItcondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#itcond}.
	 * @param ctx the parse tree
	 */
	void exitItcond(LangParser.ItcondContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LValueField}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLValueField(LangParser.LValueFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LValueField}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLValueField(LangParser.LValueFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LValueArray}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLValueArray(LangParser.LValueArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LValueArray}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLValueArray(LangParser.LValueArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LValueId}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLValueId(LangParser.LValueIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LValueId}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLValueId(LangParser.LValueIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 */
	void enterExps(LangParser.ExpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 */
	void exitExps(LangParser.ExpsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpLValue}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpLValue(LangParser.ExpLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpLValue}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpLValue(LangParser.ExpLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpUn}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpUn(LangParser.ExpUnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpUn}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpUn(LangParser.ExpUnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpFunCallReturn}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpFunCallReturn(LangParser.ExpFunCallReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpFunCallReturn}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpFunCallReturn(LangParser.ExpFunCallReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpLiteral}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpLiteral(LangParser.ExpLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpLiteral}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpLiteral(LangParser.ExpLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpOp}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpOp(LangParser.ExpOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpOp}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpOp(LangParser.ExpOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpParen}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpParen(LangParser.ExpParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpParen}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpParen(LangParser.ExpParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpNew}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpNew(LangParser.ExpNewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpNew}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpNew(LangParser.ExpNewContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(LangParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(LangParser.LiteralContext ctx);
}