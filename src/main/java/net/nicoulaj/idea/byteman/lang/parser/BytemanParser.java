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
        final PsiBuilder.Marker marker = builder.mark();
        parseScript(builder);
        marker.done(root);
        return builder.getTreeBuilt();
    }

    public static void parseScript(PsiBuilder builder) {
        while (!builder.eof()) {
            if (!(
                parseRule(builder) ||
                parseHelperStatement(builder)
            )) {
                builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-top-level-definition"));
                builder.advanceLexer(); // FIXME This should be unnecessary
            }
        }
    }

    public static boolean parseRule(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!parseRuleStatement(builder)) {
            marker.rollbackTo();
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
        marker.done(CA);
        return true;
    }

    public static boolean parseRuleStatement(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, RULE_KEYWORD)) {
            marker.rollbackTo();
            return false;
        }
        final PsiBuilder.Marker ruleNameMarker = builder.mark();
        if (!expect(builder, RULE_NAME)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-rule-name"));
        ruleNameMarker.done(RULE_NAME_ELEMENT);
        marker.done(RULE_STATEMENT);
        return true;
    }

    public static boolean parseEndruleStatement(PsiBuilder builder) {
        return expect(builder, ENDRULE_KEYWORD);
    }

    public static boolean parseClassStatement(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, CLASS_KEYWORD)) {
            marker.rollbackTo();
            return false;
        }
        expect(builder, OVERRIDE_KEYWORD);
        if (!parseClassReference(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-class-reference"));
        marker.done(CLASS_STATEMENT);
        return true;
    }

    public static boolean parseClassReference(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, CLASS_REF)) {
            marker.rollbackTo();
            return false;
        }
        marker.done(CLASS_REF_ELEMENT);
        return true;
    }


    public static boolean parseMethodStatement(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, METHOD_KEYWORD)) {
            marker.rollbackTo();
            return false;
        }
        final PsiBuilder.Marker methodNameMarker = builder.mark();
        if (!expect(builder, METHOD_REF)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-method-reference"));
        methodNameMarker.done(METHOD_REF_ELEMENT);
        marker.done(METHOD_STATEMENT);
        return true;
    }

    public static boolean parseHelperStatement(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, HELPER_KEYWORD)) {
            marker.rollbackTo();
            return false;
        }
        if (!parseClassReference(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-class-reference"));
        marker.done(HELPER_STATEMENT);
        return true;
    }

    public static boolean parseLocationStatement(PsiBuilder builder) {
        return false; // TODO
    }

    public static boolean parseBindStatement(PsiBuilder builder) {
        return false; // TODO
    }

    public static boolean parseEvent(PsiBuilder builder) {
        return false; // TODO
    }

    public static boolean parseIfStatement(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, IF_KEYWORD)) {
            marker.rollbackTo();
            return false;
        }
        if (!parseCondition(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-condition"));
        marker.done(IF_STATEMENT);
        return true;
    }

    public static boolean parseCondition(PsiBuilder builder) {
        return parseExpression(builder);
    }

    public static boolean parseDoStatement(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, DO_KEYWORD)) {
            marker.rollbackTo();
            return false;
        }
        if (!parseActions(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-actions"));
        marker.done(DO_STATEMENT);
        return true;
    }

    public static boolean parseActions(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            expect(builder, NOTHING_KEYWORD) ||
            parseActionExpressionList(builder)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(ACTIONS);
        return true;
    }

    public static boolean parseActionExpressionList(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            parseExpression(builder) && expect(builder, EXPRESSION_SEPARATOR_SET) && parseActionExpressionList(builder) ||
            parseActionExpression(builder)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(ACTION_EXPRESSION_LIST);
        return true;
    }

    public static boolean parseActionExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            parseExpression(builder) ||
            parseThrowReturnExpression(builder)
        )) {
            marker.rollbackTo();
            return false;
        }
        expect(builder, SEMI);
        marker.done(ACTION_EXPRESSION);
        return true;
    }

    public static boolean parseThrowReturnExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            expect(builder, RETURN_KEYWORD) && (parseExpression(builder) || true) ||
            expect(builder, THROW_KEYWORD) && (expect(builder, NEW_KEYWORD) || true) && parseName(builder) &&
            expect(builder, LPAREN) && (parseExpressionList(builder) || true) && expect(builder, RPAREN)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(THROW_RETURN_EXPRESSION);
        return true;
    }

    public static boolean parseExpressionList(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            parseExpression(builder) && (expect(builder, EXPRESSION_SEPARATOR_SET) && parseExpressionList(builder) || true)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(THROW_RETURN_EXPRESSION);
        return true;
    }


    public static boolean parseExpression(PsiBuilder builder) {
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
            parseMethodExpression(builder) ||
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
        final PsiBuilder.Marker marker = builder.mark();
        if (!(parseExpression(builder) && expect(builder, TERN_IF))) {
            marker.rollbackTo();
            return false;
        }
        if (!parseExpression(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-expr"));
        if (!expect(builder, COLON)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-colon"));
        if (!parseExpression(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-expr"));
        marker.done(TERNARY_EXPRESSION);
        return true;
    }

    public static boolean parseBinaryExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(parseExpression(builder) && expect(builder, BINARY_EXPRESSION_OPERATOR_SET))) {
            marker.rollbackTo();
            return false;
        }
        if (!parseExpression(builder)) builder.error(BytemanBundle.message("byteman.lang.parser.error.expected-expr"));
        marker.done(BINARY_EXPRESSION);
        return true;
    }

    public static boolean parseUnaryExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(expect(builder, UNARY_EXPRESSION_OPERATOR_SET) && parseExpression(builder))) {
            marker.rollbackTo();
            return false;
        }
        marker.done(UNARY_EXPRESSION);
        return true;
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

    public static boolean parseMethodExpression(PsiBuilder builder) {
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
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            expect(builder, INTEGER_LITERAL) ||
            expect(builder, FLOAT_LITERAL) ||
            expect(builder, TRUE_KEYWORD) ||
            expect(builder, FALSE_KEYWORD) ||
            expect(builder, STRING_LITERAL) ||
            expect(builder, DOLLAR_PREFIXED_IDENTIFIER_SET) ||
            expect(builder, LPAREN) && parseExpression(builder) && expect(builder, RPAREN)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(SIMPLE_EXPRESSION);
        return true;
    }

    public static boolean parseNullExpression(PsiBuilder builder) {
        return expect(builder, NULL_LITERAL);
    }

    public static boolean parseName(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            parseSimpleName(builder) ||
            parsePath(builder) && expect(builder, DOT) && expect(builder, IDENTIFIER)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(NAME);
        return true;
    }

    public static boolean parseSimpleName(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!expect(builder, IDENTIFIER)) {
            marker.rollbackTo();
            return false;
        }
        marker.done(SIMPLE_NAME);
        return true;
    }

    public static boolean parsePath(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            expect(builder, IDENTIFIER) ||
            parsePath(builder) && expect(builder, DOT) && expect(builder, IDENTIFIER)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(SIMPLE_EXPRESSION);
        return true;
    }
}
