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

import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

/**
 * The default styles for each of token defined for Byteman.
 * <p/>
 * Anyone who has better taste than me, feel free to contribute :)
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
class BytemanHighlighterColors implements HighlighterColors {

    /** Colors used by default. */
    public static final Color DARK_BLUE = new Color(0, 0, 128);

    /** Default style for TODO. */
    public static final TextAttributesKey KEYWORD_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.KEYWORD", new TextAttributes(DARK_BLUE, null, null, null, Font.BOLD)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey BRACKETS_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.BRACKETS", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey EXPRESSION_SEPARATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.EXPRESSION_SEPARATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey BINDING_SEPARATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.BINDING_SEPARATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey IDENTIFIER_PUNCTUATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.IDENTIFIER_PUNCTUATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey ASSIGN_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.ASSIGN_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey LOGICAL_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.LOGICAL_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey COMPARISON_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.COMPARISON_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey BYTEWISE_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.BYTEWISE_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey ARITHMETIC_OPERATOR_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.ARITHMETIC_OPERATOR", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey TERNARY_CONDITION_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.TERNARY_CONDITION", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey DOLLAR_PREFIXED_IDENTIFIER_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.DOLLAR_PREFIXED_IDENTIFIER", new TextAttributes(null, null, Color.BLACK, EffectType.LINE_UNDERSCORE, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey IDENTIFIER_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.IDENTIFIER", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey NUMBER_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.NUMBER", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey STRING_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.STRING", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for TODO. */
    public static final TextAttributesKey WHITE_SPACE_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.WHITE_SPACE", new TextAttributes(null, null, null, null, Font.PLAIN)
    );

    /** Default style for comments. */
    public static final TextAttributesKey COMMENT_ATTR_KEY = TextAttributesKey.createTextAttributesKey(
            "BYTEMAN.COMMENT", new TextAttributes(Color.GRAY, null, null, null, Font.ITALIC)
    );
}
