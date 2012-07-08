/*
 * Copyright (c) 2011-2012 Julien Nicoulaud <julien.nicoulaud@gmail.com>
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
import net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.nicoulaj.idea.byteman.highlighter.BytemanHighlighterColors.*;
import static net.nicoulaj.idea.byteman.lang.BytemanTokenTypeSets.*;

/**
 * {@link com.intellij.openapi.fileTypes.SyntaxHighlighter} implementation for the Byteman language.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanSyntaxHighlighter extends SyntaxHighlighterBase {

    /** The {@link Lexer} instance. */
    protected final Lexer lexer = new BytemanLexer();

    /** The map of text attribute keys for each token type. */
    protected static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    static {
        fillMap(ATTRIBUTES, KEYWORD_SET, KEYWORD_ATTR_KEY);
        fillMap(ATTRIBUTES, BRACKETS_SET, BRACKETS_ATTR_KEY);
        fillMap(ATTRIBUTES, EXPRESSION_SEPARATOR_SET, EXPRESSION_SEPARATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BINDING_SEPARATOR_SET, BINDING_SEPARATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, IDENTIFIER_PUNCTUATOR_SET, IDENTIFIER_PUNCTUATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, ASSIGN_OPERATOR_SET, ASSIGN_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, LOGICAL_OPERATOR_SET, LOGICAL_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, COMPARISON_OPERATOR_SET, COMPARISON_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, BITWISE_OPERATOR_SET, BYTEWISE_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, ARITHMETIC_OPERATOR_SET, ARITHMETIC_OPERATOR_ATTR_KEY);
        fillMap(ATTRIBUTES, TERNARY_CONDITION_SET, TERNARY_CONDITION_ATTR_KEY);
        fillMap(ATTRIBUTES, DOLLAR_PREFIXED_IDENTIFIER_SET, DOLLAR_PREFIXED_IDENTIFIER_ATTR_KEY);
        fillMap(ATTRIBUTES, IDENTIFIER_SET, IDENTIFIER_ATTR_KEY);
        fillMap(ATTRIBUTES, NUMBER_SET, NUMBER_ATTR_KEY);
        fillMap(ATTRIBUTES, STRING_SET, STRING_ATTR_KEY);
        fillMap(ATTRIBUTES, WHITE_SPACE_SET, WHITE_SPACE_ATTR_KEY);
        fillMap(ATTRIBUTES, COMMENT_SET, COMMENT_ATTR_KEY);
        fillMap(ATTRIBUTES, ERROR_SET, BAD_CHARACTER);
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
