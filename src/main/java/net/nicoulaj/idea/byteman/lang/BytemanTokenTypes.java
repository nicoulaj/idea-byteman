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
package net.nicoulaj.idea.byteman.lang;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

/**
 * Lexer tokens for the Byteman language.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public interface BytemanTokenTypes extends TokenType {

    /**
     * TODO token type.
     */
    IElementType BIND_KEYWORD = new BytemanElementType("BIND_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType IF_KEYWORD = new BytemanElementType("IF_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType DO_KEYWORD = new BytemanElementType("DO_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType RULE_KEYWORD = new BytemanElementType("RULE_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType CLASS_KEYWORD = new BytemanElementType("CLASS_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType METHOD_KEYWORD = new BytemanElementType("METHOD_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType LINE_KEYWORD = new BytemanElementType("LINE_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType HELPER_KEYWORD = new BytemanElementType("HELPER_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType ENDRULE_KEYWORD = new BytemanElementType("ENDRULE_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType NOTHING_KEYWORD = new BytemanElementType("NOTHING_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType TRUE_KEYWORD = new BytemanElementType("TRUE_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType FALSE_KEYWORD = new BytemanElementType("FALSE_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType RETURN_KEYWORD = new BytemanElementType("RETURN_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType THROW_KEYWORD = new BytemanElementType("THROW_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType NEW_KEYWORD = new BytemanElementType("NEW_KEYWORD");

    /**
     * TODO token type.
     */
    IElementType COMMENT = new BytemanElementType("COMMENT");

    /**
     * TODO token type.
     */
    IElementType LPAREN = new BytemanElementType("LPAREN");

    /**
     * TODO token type.
     */
    IElementType RPAREN = new BytemanElementType("RPAREN");

    /**
     * TODO token type.
     */
    IElementType LSQUARE = new BytemanElementType("LSQUARE");

    /**
     * TODO token type.
     */
    IElementType RSQUARE = new BytemanElementType("RSQUARE");

    /**
     * TODO token type.
     */
    IElementType LBRACE = new BytemanElementType("LBRACE");

    /**
     * TODO token type.
     */
    IElementType RBRACE = new BytemanElementType("RBRACE");

    /**
     * TODO token type.
     */
    IElementType SEMI = new BytemanElementType("SEMI");

    /**
     * TODO token type.
     */
    IElementType COMMA = new BytemanElementType("COMMA");

    /**
     * TODO token type.
     */
    IElementType DOT = new BytemanElementType("DOT");

    /**
     * TODO token type.
     */
    IElementType ASSIGN = new BytemanElementType("ASSIGN");

    /**
     * TODO token type.
     */
    IElementType AND = new BytemanElementType("AND");

    /**
     * TODO token type.
     */
    IElementType OR = new BytemanElementType("OR");

    /**
     * TODO token type.
     */
    IElementType NOT = new BytemanElementType("NOT");

    /**
     * TODO token type.
     */
    IElementType LT = new BytemanElementType("LT");

    /**
     * TODO token type.
     */
    IElementType LE = new BytemanElementType("LE");

    /**
     * TODO token type.
     */
    IElementType EQ = new BytemanElementType("EQ");

    /**
     * TODO token type.
     */
    IElementType NE = new BytemanElementType("NE");

    /**
     * TODO token type.
     */
    IElementType GE = new BytemanElementType("GE");

    /**
     * TODO token type.
     */
    IElementType GT = new BytemanElementType("GT");

    /**
     * TODO token type.
     */
    IElementType BOR = new BytemanElementType("BOR");

    /**
     * TODO token type.
     */
    IElementType BAND = new BytemanElementType("BAND");

    /**
     * TODO token type.
     */
    IElementType BXOR = new BytemanElementType("BXOR");

    /**
     * TODO token type.
     */
    IElementType TWIDDLE = new BytemanElementType("TWIDDLE");

    /**
     * TODO token type.
     */
    IElementType MUL = new BytemanElementType("MUL");

    /**
     * TODO token type.
     */
    IElementType DIV = new BytemanElementType("DIV");

    /**
     * TODO token type.
     */
    IElementType PLUS = new BytemanElementType("PLUS");

    /**
     * TODO token type.
     */
    IElementType MINUS = new BytemanElementType("MINUS");

    /**
     * TODO token type.
     */
    IElementType MOD = new BytemanElementType("MOD");

    /**
     * TODO token type.
     */
    IElementType TERN_IF = new BytemanElementType("TERN_IF");

    /**
     * TODO token type.
     */
    IElementType COLON = new BytemanElementType("COLON");

    /**
     * TODO token type.
     */
    IElementType DOLLAR = new BytemanElementType("DOLLAR");

    /**
     * TODO token type.
     */
    IElementType NULL_LITERAL = new BytemanElementType("NULL_LITERAL");

    /**
     * TODO token type.
     */
    IElementType IDENTIFIER = new BytemanElementType("IDENTIFIER");

    /**
     * TODO token type.
     */
    IElementType INTEGER_LITERAL = new BytemanElementType("INTEGER_LITERAL");

    /**
     * TODO token type.
     */
    IElementType FLOAT_LITERAL = new BytemanElementType("INTEGER_LITERAL");

    /**
     * TODO token type.
     */
    IElementType STRING_LITERAL = new BytemanElementType("STRING_LITERAL");

    /**
     * TODO token type.
     */
    IElementType QUOTEDIDENT = new BytemanElementType("QUOTEDIDENT");

    /**
     * TODO token type.
     */
    IElementType RULE_NAME = new BytemanElementType("RULE_NAME");

    /**
     * TODO token type.
     */
    IElementType CLASS_REF = new BytemanElementType("CLASS_REF");

    /**
     * TODO token type.
     */
    IElementType METHOD_REF = new BytemanElementType("METHOD_REF");

    /**
     * TODO token type.
     */
    IElementType OVERRIDE_KEYWORD = new BytemanElementType("OVERRIDE_KEYWORD");
}
