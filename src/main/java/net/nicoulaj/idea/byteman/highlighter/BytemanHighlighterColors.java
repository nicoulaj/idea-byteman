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

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

/**
 * The default styles for each of token defined for Byteman.
 * <p/>
 * Anyone who has better taste than me, feel free to contribute :)
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class BytemanHighlighterColors {

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey KEYWORD_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.KEYWORD", new TextAttributes(Color.BLUE, null, null, null, Font.BOLD)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey BRACKETS_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.BRACKETS", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey EXPRESSION_SEPARATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.EXPRESSION_SEPARATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey BINDING_SEPARATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.BINDING_SEPARATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey IDENTIFIER_PUNCTUATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.IDENTIFIER_PUNCTUATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey ASSIGN_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.ASSIGN_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey LOGICAL_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.LOGICAL_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey COMPARISON_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.COMPARISON_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey BYTEWISE_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.BYTEWISE_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey ARITHMETIC_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.ARITHMETIC_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey TERNARY_CONDITION_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.TERNARY_CONDITION", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey DOLLAR_PREFIXED_IDENTIFIER_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.DOLLAR_PREFIXED_IDENTIFIER", new TextAttributes(null, null, Color.BLACK, EffectType.LINE_UNDERSCORE, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey IDENTIFIER_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.IDENTIFIER", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey NUMBER_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.NUMBER", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey STRING_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.STRING", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for TODO.
     */
    public static TextAttributesKey WHITE_SPACE_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.WHITE_SPACE", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /**
     * Default style for comments.
     */
    public static TextAttributesKey COMMENT_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.COMMENT", new TextAttributes(Color.GRAY, null, null, null, Font.ITALIC)
    );

    /**
     * Default style for errors.
     */
    public static TextAttributesKey ERROR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.ERROR", new TextAttributes(Color.RED, null, null, null, Font.BOLD)
    );
}
