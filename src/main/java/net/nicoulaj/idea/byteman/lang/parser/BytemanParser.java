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
                builder.advanceLexer();
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
        return false;
    }

    public static boolean parseBindStatement(PsiBuilder builder) {
        return false;
    }

    public static boolean parseIfStatement(PsiBuilder builder) {
        return false;
    }

    public static boolean parseDoStatement(PsiBuilder builder) {
        return false;
    }
}
