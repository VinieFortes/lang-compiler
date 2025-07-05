// Generated from src/main/antlr4/Lang.g4 by ANTLR 4.13.2
package langcompiler;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(LangParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(LangParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(LangParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(LangParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#fun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFun(LangParser.FunContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(LangParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(LangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBtype(LangParser.BtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdBlock}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdBlock(LangParser.CmdBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdIf}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIf(LangParser.CmdIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdIterate}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIterate(LangParser.CmdIterateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdRead}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRead(LangParser.CmdReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdPrint}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPrint(LangParser.CmdPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdReturn}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdReturn(LangParser.CmdReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdAssign}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAssign(LangParser.CmdAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmdFunCall}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFunCall(LangParser.CmdFunCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#itcond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItcond(LangParser.ItcondContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(LangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LValueField}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValueField(LangParser.LValueFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LValueArray}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValueArray(LangParser.LValueArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LValueId}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValueId(LangParser.LValueIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExps(LangParser.ExpsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpEquality}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpEquality(LangParser.ExpEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpOp}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpOp(LangParser.ExpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpOp}
	 * labeled alternative in {@link LangParser#equalityExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpOp(LangParser.EqualityExpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpRelational}
	 * labeled alternative in {@link LangParser#equalityExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpRelational(LangParser.EqualityExpRelationalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpOp}
	 * labeled alternative in {@link LangParser#relationalExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpOp(LangParser.RelationalExpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpAdditive}
	 * labeled alternative in {@link LangParser#relationalExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpAdditive(LangParser.RelationalExpAdditiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpMultiplicative}
	 * labeled alternative in {@link LangParser#additiveExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpMultiplicative(LangParser.AdditiveExpMultiplicativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpOp}
	 * labeled alternative in {@link LangParser#additiveExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpOp(LangParser.AdditiveExpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpUnary}
	 * labeled alternative in {@link LangParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpUnary(LangParser.MultiplicativeExpUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpOp}
	 * labeled alternative in {@link LangParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpOp(LangParser.MultiplicativeExpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpOp}
	 * labeled alternative in {@link LangParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpOp(LangParser.UnaryExpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpPrimary}
	 * labeled alternative in {@link LangParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpPrimary(LangParser.UnaryExpPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpLValue}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpLValue(LangParser.PrimaryExpLValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpFunCallReturn}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpFunCallReturn(LangParser.PrimaryExpFunCallReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpNew}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpNew(LangParser.PrimaryExpNewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpLiteral}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpLiteral(LangParser.PrimaryExpLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpParen}
	 * labeled alternative in {@link LangParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpParen(LangParser.PrimaryExpParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(LangParser.LiteralContext ctx);
}