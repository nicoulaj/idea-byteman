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
        marker.done(EXPRESSION_LIST);
        return true;
    }


    public static boolean parseExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!((expect(builder, DOLLAR_PREFIXED_IDENTIFIER_SET) || parseFieldExpression(builder) || parseArrayExpression(builder) || parseSimpleName(builder)) &&
              expect(builder, ASSIGN) &&
              parseExpression(builder))) {
            marker.rollbackTo();
            if (!(
                parseTernaryExpression(builder) ||
                parseBinaryExpression(builder) ||
                parseUnaryExpression(builder) ||
                parseArrayExpression(builder) ||
                parseFieldExpression(builder) ||
                parseMethodExpression(builder) ||
                parseNewExpression(builder) ||
                parseSimpleExpression(builder) ||
                parseNullExpression(builder) ||
                parseSimpleName(builder)
            )) {
                marker.rollbackTo();
                return false;
            }
        }
        marker.done(EXPRESSION_STATEMENT);
        return true;
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
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            (parseSimpleExpression(builder) || parseSimpleName(builder) || parseFieldExpression(builder) || parseMethodExpression(builder)) &&
            parseArrayIdxList(builder)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(ARRAY_EXPRESSION);
        return true;
    }

    public static boolean parseArrayIdxList(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(parseArrayIdx(builder) && (parseArrayIdxList(builder) || true))) {
            marker.rollbackTo();
            return false;
        }
        marker.done(ARRAY_IDX_LIST);
        return true;
    }

    public static boolean parseArrayIdx(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(expect(builder, LSQUARE) && parseExpression(builder) && expect(builder, RSQUARE))) {
            marker.rollbackTo();
            return false;
        }
        marker.done(ARRAY_IDX);
        return true;
    }

    public static boolean parseFieldExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            parseExpressionFieldExpression(builder) ||
            parsePath(builder) && expect(builder, DOT) && parseSimpleName(builder)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(FIELD);
        return true;
    }

    public static boolean parseExpressionFieldExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            (parseSimpleExpression(builder) || parseMethodExpression(builder) || parseExpressionFieldExpression(builder)) &&
            expect(builder, DOT) &&
            parseSimpleName(builder)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(EXPRESSION_FIELD_EXPRESSION);
        return true;
    }

    public static boolean parseMethodExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(parseExpressionMethodExpression(builder) ||
              (parseSimpleName(builder) || parsePath(builder)) && expect(builder, LPAREN) && (parseExpressionList(builder) || true) && expect(builder, RPAREN)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(METHOD_EXPRESSION);
        return true;
    }

    public static boolean parseExpressionMethodExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            (parseSimpleExpression(builder) || parseMethodExpression(builder) || parseExpressionFieldExpression(builder)) &&
            expect(builder, DOT) &&
            parseSimpleName(builder) &&
            expect(builder, LPAREN) &&
            (parseExpressionList(builder) || true) &&
            expect(builder, RPAREN)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(EXPRESSION_METHOD_EXPRESSION);
        return true;
    }

    public static boolean parseNewExpression(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            expect(builder, NEW_KEYWORD) &&
            parseName(builder) &&
            (parseNewArrayIdxList(builder) || expect(builder, LPAREN) && (parseExpressionList(builder) || true) && expect(builder, RPAREN))
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(NEW_EXPRESSION);
        return true;
    }

    public static boolean parseNewArrayIdxList(PsiBuilder builder) {
        final PsiBuilder.Marker marker = builder.mark();
        if (!(
            expect(builder, LSQUARE) &&
            (parseExpression(builder) || true) &&
            expect(builder, RSQUARE) &&
            (parseNewArrayIdxList(builder) || true)
        )) {
            marker.rollbackTo();
            return false;
        }
        marker.done(NEW_ARRAY_IDX_LIST);
        return true;
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
        marker.done(PATH);
        return true;
    }
}
