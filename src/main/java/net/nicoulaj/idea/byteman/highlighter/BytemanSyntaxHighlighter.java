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
package net.nicoulaj.idea.byteman.highlighter;

import com.intellij.lexer.EmptyLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import net.nicoulaj.idea.byteman.lang.BytemanTokenTypeSets;
import net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link com.intellij.openapi.fileTypes.SyntaxHighlighter} implementation for the Byteman language.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class BytemanSyntaxHighlighter extends SyntaxHighlighterBase {

    /**
     * The {@link Lexer} instance.
     */
    protected final Lexer lexer = new BytemanLexer();

    /**
     * The map of text attribute keys for each token type.
     */
    protected static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    static {
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.KEYWORD_SET, BytemanHighlighterColors.KEYWORD_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.BRACKETS_SET, BytemanHighlighterColors.BRACKETS_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.EXPRESSION_SEPARATOR_SET, BytemanHighlighterColors.EXPRESSION_SEPARATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.BINDING_SEPARATOR_SET, BytemanHighlighterColors.BINDING_SEPARATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.IDENTIFIER_PUNCTUATOR_SET, BytemanHighlighterColors.IDENTIFIER_PUNCTUATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.ASSIGN_OPERATOR_SET, BytemanHighlighterColors.ASSIGN_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.LOGICAL_OPERATOR_SET, BytemanHighlighterColors.LOGICAL_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.COMPARISON_OPERATOR_SET, BytemanHighlighterColors.COMPARISON_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.BYTEWISE_OPERATOR_SET, BytemanHighlighterColors.BYTEWISE_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.ARITHMETIC_OPERATOR_SET, BytemanHighlighterColors.ARITHMETIC_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.TERNARY_CONDITION_SET, BytemanHighlighterColors.TERNARY_CONDITION_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.DOLLAR_PREFIXED_IDENTIFIER_SET, BytemanHighlighterColors.DOLLAR_PREFIXED_IDENTIFIER_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.IDENTIFIER_SET, BytemanHighlighterColors.IDENTIFIER_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.NUMBER_SET, BytemanHighlighterColors.NUMBER_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.STRING_SET, BytemanHighlighterColors.STRING_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.WHITE_SPACE_SET, BytemanHighlighterColors.WHITE_SPACE_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.COMMENT_SET, BytemanHighlighterColors.COMMENT_ATTR_KEY);
        fillMap(ATTRIBUTES, BytemanTokenTypeSets.ERROR_SET, BytemanHighlighterColors.BAD_CHARACTER);
    }

    /**
     * Get the lexer used for highlighting a Byteman file.
     *
     * @return an {@link EmptyLexer}.
     * @see #lexer
     */
    @NotNull
    public Lexer getHighlightingLexer() {
        return lexer;
    }

    /**
     * Get the list of text attribute keys used for highlighting the specified token type.
     *
     * @param tokenType the token type
     * @return an array of {@link TextAttributesKey}
     */
    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}
