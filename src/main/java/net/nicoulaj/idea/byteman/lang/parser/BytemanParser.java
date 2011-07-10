/*
 * Copyright (c) 2011 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 *
 * This file is part of idea-byteman.
 *
 * idea-byteman is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * idea-byteman is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with idea-byteman.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.nicoulaj.idea.byteman.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import net.nicoulaj.idea.byteman.BytemanBundle;
import net.nicoulaj.idea.byteman.lang.BytemanElementTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.lang.PsiBuilderUtil.expect;

/**
 * Parser implementation for Byteman.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class BytemanParser implements PsiParser, BytemanElementTypes {

    /**
     * Parse the contents of the specified PSI builder and returns an AST tree with the specified type of root element.
     *
     * @param root    the type of the root element in the AST tree.
     * @param builder the builder which is used to retrieve the original file tokens and build the AST tree.
     * @return the root of the resulting AST tree.
     */
    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        builder.setDebugMode(true);
        final PsiBuilder.Marker rootMarker = builder.mark();
        parseScript(builder);
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    public static void parseScript(PsiBuilder builder) {
        while (!builder.eof()) {
            if (!parseRule(builder) && !parseHelperStatement(builder)) {
                builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-top-level-definition"));
                builder.advanceLexer(); // FIXME This should be unnecessary
            }
        }
    }

    public static boolean parseRule(PsiBuilder builder) {
        final PsiBuilder.Marker caMarker = builder.mark();
        if (!parseRuleStatement(builder)) {
            caMarker.rollbackTo();
            return false;
        }
        if (!parseClassStatement(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-class-statement"));
        if (!parseMethodStatement(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-method-statement"));
        parseHelperStatement(builder);
        parseLocationStatement(builder);
        parseBindStatement(builder);
        if (!parseIfStatement(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-if-statement"));
        if (!parseDoStatement(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-do-statement"));
        if (!parseEndruleStatement(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-endrule-statement"));
        caMarker.done(CA);
        return true;
    }

    public static boolean parseRuleStatement(PsiBuilder builder) {
        final PsiBuilder.Marker ruleStatementMarker = builder.mark();
        if (!expect(builder, RULE_KEYWORD)) {
            ruleStatementMarker.rollbackTo();
            return false;
        }
        final PsiBuilder.Marker ruleNameMarker = builder.mark();
        if (!expect(builder, RULE_NAME)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-rule-name"));
        ruleNameMarker.done(RULE_NAME_ELEMENT);
        ruleStatementMarker.done(RULE_STATEMENT);
        return true;
    }

    public static boolean parseEndruleStatement(PsiBuilder builder) {
        return expect(builder, ENDRULE_KEYWORD);
    }

    public static boolean parseClassStatement(PsiBuilder builder) {
        final PsiBuilder.Marker classStatementMarker = builder.mark();
        if (!expect(builder, CLASS_KEYWORD)) {
            classStatementMarker.rollbackTo();
            return false;
        }
        expect(builder, OVERRIDE_KEYWORD);
        if (!parseClassReference(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-class-reference"));
        classStatementMarker.done(CLASS_STATEMENT);
        return true;
    }

    public static boolean parseClassReference(PsiBuilder builder) {
        final PsiBuilder.Marker classReferenceMarker = builder.mark();
        if (!expect(builder, CLASS_REF)) {
            classReferenceMarker.rollbackTo();
            return false;
        }
        classReferenceMarker.done(CLASS_REF_ELEMENT);
        return true;
    }


    public static boolean parseMethodStatement(PsiBuilder builder) {
        final PsiBuilder.Marker methodStatementMarker = builder.mark();
        if (!expect(builder, METHOD_KEYWORD)) {
            methodStatementMarker.rollbackTo();
            return false;
        }
        final PsiBuilder.Marker methodNameMarker = builder.mark();
        if (!expect(builder, METHOD_REF)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-method-reference"));
        methodNameMarker.done(METHOD_REF_ELEMENT);
        methodStatementMarker.done(METHOD_STATEMENT);
        return true;
    }

    public static boolean parseHelperStatement(PsiBuilder builder) {
        final PsiBuilder.Marker helperStatementMarker = builder.mark();
        if (!expect(builder, HELPER_KEYWORD)) {
            helperStatementMarker.rollbackTo();
            return false;
        }
        if (!parseClassReference(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-class-reference"));
        helperStatementMarker.done(HELPER_STATEMENT);
        return true;
    }

    public static boolean parseLocationStatement(PsiBuilder builder) {
        return false; // TODO
    }

    public static boolean parseBindStatement(PsiBuilder builder) {
        return false; // TODO
    }

    public static boolean parseIfStatement(PsiBuilder builder) {
        return parseExpressionStatement(builder);
    }

    public static boolean parseExpressionStatement(PsiBuilder builder) {
//        expr
//            ::= ternary_oper_expr:e {: RESULT = e; :}
//            |	binary_oper_expr:e {: RESULT = e; :}
//            |	unary_oper_expr:e {: RESULT = e; :}
//            |	array_expr:e {: RESULT = e; :}
//            |	field_expr:e {: RESULT = e; :}
//            |	meth_expr:e {: RESULT = e; :}
//            |   new_expr:ne {: RESULT = ne; :}
//            |	simple_expr:e {: RESULT = e; :}
//            |   null_expr:e {: RESULT = e; :}
//            |	simple_name:n {: RESULT = n; :}
//            |   simple_name:s ASSIGN expr:e {: RESULT = node(ParseNode.ASSIGN, sleft, sright, s, e); :}
//            |   DOLLAR:d ASSIGN expr:e {: RESULT = node(ParseNode.ASSIGN, dleft, dright, node(ParseNode.DOLLAR, dleft, dright, d), e); :}
//            |   field_expr:f ASSIGN expr:e {: RESULT = node(ParseNode.ASSIGN, fleft, fright, f, e); :}
//            |   array_expr:a ASSIGN expr:e {: RESULT = node(ParseNode.ASSIGN, aleft, aright, a, e); :}
//            |   error:err expr:e {: error("invalid expression", errleft, errright); RESULT = e; :}
//            ;
        final PsiBuilder.Marker expressionStatementMarker = builder.mark();
        if (!(
        parseTernaryExpression(builder) ||
        parseBinaryExpression(builder) ||
        parseUnaryExpression(builder) ||
        parseArrayExpression(builder) ||
        parseFieldExpression(builder) ||
        parseMethExpression(builder) ||
        parseNewExpression(builder) ||
        parseSimpleExpression(builder) ||
        parseNullExpression(builder)
        )) {
            expressionStatementMarker.rollbackTo();
            return false;
        }
        expressionStatementMarker.done(EXPRESSION_STATEMENT);
        return false;
    }

    public static boolean parseTernaryExpression(PsiBuilder builder) {
//        ternary_oper_expr
//	::=	expr:cond TERN_IF expr:iftrue COLON expr:iffalse
//		{: RESULT = node(ParseNode.TERNOP, condleft, condright, cond, iftrue, iffalse); :}
//	;
        return false; // TODO
    }

    public static boolean parseBinaryExpression(PsiBuilder builder) {
//binary_oper_expr
//	// logical operators
//	::=	expr:e1 OR:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.OR, oleft, oright), e1, e2); :}
//	|	expr:e1 AND:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.AND, oleft, oright), e1, e2); :}
//	// comparison operators
//	|	expr:e1 LT:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.LT, oleft, oright), e1, e2); :}
//	|	expr:e1 LE:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.LE, oleft, oright), e1, e2); :}
//	|	expr:e1 EQ:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.EQ, oleft, oright), e1, e2); :}
//	|	expr:e1 NE:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.NE, oleft, oright), e1, e2); :}
//	|	expr:e1 GE:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.GE, oleft, oright), e1, e2); :}
//	|	expr:e1 GT:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.GT, oleft, oright), e1, e2); :}
//	// bitwise operators
//	|	expr:e1 BOR:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.BOR, oleft, oright), e1, e2); :}
//	|	expr:e1 BAND:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.BAND, oleft, oright), e1, e2); :}
//	|	expr:e1 BXOR:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.BXOR, oleft, oright), e1, e2); :}
//	// arithmetic operators
//	| expr:e1 PLUS:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.PLUS, oleft, oright), e1, e2); :}
//	|	expr:e1 MINUS:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.MINUS, oleft, oright), e1, e2); :}
//	|	expr:e1 MUL:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.MUL, oleft, oright), e1, e2); :}
//	|	expr:e1 DIV:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.DIV, oleft, oright), e1, e2); :}
//	|	expr:e1 MOD:o expr:e2 {: RESULT = node(ParseNode.BINOP, e1left, e1right, node(ParseNode.MOD, oleft, oright), e1, e2); :}
//	;
        return false; // TODO
    }

    public static boolean parseUnaryExpression(PsiBuilder builder) {
//	/* logical operators */
//	::=	NOT:o expr:e {: RESULT = node(ParseNode.UNOP, eleft, eright, node(ParseNode.NOT, oleft, oright), e); :}
//	/* bitwise operators */
//	|	TWIDDLE:o expr:e {: RESULT = node(ParseNode.UNOP, eleft, eright, node(ParseNode.TWIDDLE, oleft, oright), e); :}
//	/* arithmetic operators */
//    |   MINUS:o expr:e {: RESULT = node(ParseNode.UNOP, eleft, eright, node(ParseNode.UMINUS, oleft, oright), e); :} %prec UMINUS
//	;
        return false; // TODO
    }

    public static boolean parseArrayExpression(PsiBuilder builder) {
//array_expr
//	::=	simple_expr:se array_idx_list:ail
//		{: RESULT = node(ParseNode.ARRAY, seleft, seright, se, ail); :}
//	|	simple_name:name array_idx_list:ail
//		{: RESULT = node(ParseNode.ARRAY, nameleft, nameright, name, ail); :}
//	|	field_expr:fe array_idx_list:ail
//		{: RESULT = node(ParseNode.ARRAY, feleft, feright, fe, ail); :}
//	|	meth_expr:me array_idx_list:ail
//		{: RESULT = node(ParseNode.ARRAY, meleft, meright, me, ail); :}
//	;
        return false; // TODO
    }

    public static boolean parseFieldExpression(PsiBuilder builder) {
//	::=	path:p DOT simple_name:f
//		{: RESULT = node(ParseNode.FIELD, fleft, fright, f, p); :}
//	|	expr_field_expr:efe {: RESULT = efe; :}
//	;
        return false; // TODO
    }

    public static boolean parseMethExpression(PsiBuilder builder) {
//	::=	simple_name:m LPAREN RPAREN
//		{: RESULT = node(ParseNode.METH, mleft, mright, m, null, null); :}
//	|	simple_name:m LPAREN expr_list:args RPAREN
//		{: RESULT = node(ParseNode.METH, mleft, mright, m, null, args); :}
//	|	path:p DOT simple_name:m LPAREN RPAREN
//		{: RESULT = node(ParseNode.METH, mleft, mright, m, p, null); :}
//	|	path:p DOT simple_name:m LPAREN expr_list:args RPAREN
//		{: RESULT = node(ParseNode.METH, mleft, mright, m, p, args); :}
//	|	expr_meth_expr:eme {: RESULT = eme; :}
        return false; // TODO
    }

    public static boolean parseNewExpression(PsiBuilder builder) {
//new_expr
//    ::=	NEW name:i LPAREN RPAREN
//		{: RESULT = node(ParseNode.NEW, ileft, iright, i, null, null); :}
//	|	NEW name:i new_array_idx_list:as
//		{: RESULT = node(ParseNode.NEW, ileft, iright, i, null, as); :}
//	|	NEW name:i LPAREN expr_list:args RPAREN
//		{: RESULT = node(ParseNode.NEW, ileft, iright, i, args, null); :}
//	;
        return false; // TODO
    }

    public static boolean parseSimpleExpression(PsiBuilder builder) {
//simple_expr
//	::=	INTEGER_LITERAL:i
//		{: RESULT =  node(ParseNode.INTEGER_LITERAL, ileft, iright, i); :}
//	|	FLOAT_LITERAL:f
//		{: RESULT =  node(ParseNode.FLOAT_LITERAL, fleft, fright, f); :}
//	|	BOOLEAN_LITERAL:b
//		{: RESULT =  node(ParseNode.BOOLEAN_LITERAL, bleft, bright, b); :}
//	|	STRING_LITERAL:s
//		{: RESULT = node(ParseNode.STRING_LITERAL, sleft, sright, s); :}
//	|	DOLLAR:s
//	    {: RESULT = node(ParseNode.DOLLAR, sleft, sright, s); :}
//	|	LPAREN expr:e RPAREN
//		{: RESULT = e; :}
//	;
        return false; // TODO
    }

    public static boolean parseNullExpression(PsiBuilder builder) {
//null_expr
//    ::=	NULL_LITERAL:n
//		{: RESULT = node(ParseNode.NULL_LITERAL, nleft, nright); :}
//	;
        return false; // TODO
    }

    public static boolean parseDoStatement(PsiBuilder builder) {
//        actions	::=	NOTHING:n {: RESULT = node(ParseNode.NOTHING, nleft, nright); :}
//            |	action_expr_list:ael {: RESULT = ael; :}
//            ;

        return false; // TODO
    }
}
