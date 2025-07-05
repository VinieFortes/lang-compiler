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
	 * Enter a parse tree produced by the {@code ExpEquality}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpEquality(LangParser.ExpEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpEquality}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpEquality(LangParser.ExpEqualityContext ctx);
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
	 * Enter a parse tree produced by the {@code EqualityExpOp}
	 * labeled alternative in {@link LangParser#equalityExp}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpOp(LangParser.EqualityExpOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpOp}
	 * labeled alternative in {@link LangParser#equalityExp}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpOp(LangParser.EqualityExpOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpRelational}
	 * labeled alternative in {@link LangParser#equalityExp}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpRelational(LangParser.EqualityExpRelationalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpRelational}
	 * labeled alternative in {@link LangParser#equalityExp}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpRelational(LangParser.EqualityExpRelationalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpOp}
	 * labeled alternative in {@link LangParser#relationalExp}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpOp(LangParser.RelationalExpOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpOp}
	 * labeled alternative in {@link LangParser#relationalExp}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpOp(LangParser.RelationalExpOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpAdditive}
	 * labeled alternative in {@link LangParser#relationalExp}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpAdditive(LangParser.RelationalExpAdditiveContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpAdditive}
	 * labeled alternative in {@link LangParser#relationalExp}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpAdditive(LangParser.RelationalExpAdditiveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpMultiplicative}
	 * labeled alternative in {@link LangParser#additiveExp}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpMultiplicative(LangParser.AdditiveExpMultiplicativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpMultiplicative}
	 * labeled alternative in {@link LangParser#additiveExp}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpMultiplicative(LangParser.AdditiveExpMultiplicativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpOp}
	 * labeled alternative in {@link LangParser#additiveExp}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpOp(LangParser.AdditiveExpOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpOp}
	 * labeled alternative in {@link LangParser#additiveExp}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpOp(LangParser.AdditiveExpOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpUnary}
	 * labeled alternative in {@link LangParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpUnary(LangParser.MultiplicativeExpUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpUnary}
	 * labeled alternative in {@link LangParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpUnary(LangParser.MultiplicativeExpUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpOp}
	 * labeled alternative in {@link LangParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpOp(LangParser.MultiplicativeExpOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpOp}
	 * labeled alternative in {@link LangParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpOp(LangParser.MultiplicativeExpOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpOp}
	 * labeled alternative in {@link LangParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpOp(LangParser.UnaryExpOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpOp}
	 * labeled alternative in {@link LangParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpOp(LangParser.UnaryExpOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpPrimary}
	 * labeled alternative in {@link LangParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpPrimary(LangParser.UnaryExpPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpPrimary}
	 * labeled alternative in {@link LangParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpPrimary(LangParser.UnaryExpPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpLValue}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpLValue(LangParser.PrimaryExpLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpLValue}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpLValue(LangParser.PrimaryExpLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpFunCallReturn}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpFunCallReturn(LangParser.PrimaryExpFunCallReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpFunCallReturn}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpFunCallReturn(LangParser.PrimaryExpFunCallReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpNew}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpNew(LangParser.PrimaryExpNewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpNew}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpNew(LangParser.PrimaryExpNewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpLiteral}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpLiteral(LangParser.PrimaryExpLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpLiteral}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpLiteral(LangParser.PrimaryExpLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpParen}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpParen(LangParser.PrimaryExpParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpParen}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpParen(LangParser.PrimaryExpParenContext ctx);
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