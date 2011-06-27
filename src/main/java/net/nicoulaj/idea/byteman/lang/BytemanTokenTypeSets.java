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

import com.intellij.psi.tree.TokenSet;

/**
 * Token type sets for the Byteman language.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public interface BytemanTokenTypeSets extends BytemanTokenTypes {

    /**
     * TODO token type set.
     */
    TokenSet KEYWORD_SET = TokenSet.create(BIND_KEYWORD,
                                           IF_KEYWORD,
                                           DO_KEYWORD,
                                           RULE_KEYWORD,
                                           CLASS_KEYWORD,
                                           METHOD_KEYWORD,
                                           LINE_KEYWORD,
                                           ENDRULE_KEYWORD,
                                           NOTHING_KEYWORD,
                                           TRUE_KEYWORD,
                                           FALSE_KEYWORD,
                                           RETURN_KEYWORD,
                                           THROW_KEYWORD,
                                           NEW_KEYWORD);

    /**
     * TODO token type set.
     */
    TokenSet BRACKETS_SET = TokenSet.create(LBRACE,
                                            RBRACE,
                                            LPAREN,
                                            RPAREN,
                                            LSQUARE,
                                            RSQUARE);

    /**
     * TODO token type set.
     */
    TokenSet EXPRESSION_SEPARATOR_SET = TokenSet.create(SEMI);

    /**
     * TODO token type set.
     */
    TokenSet BINDING_SEPARATOR_SET = TokenSet.create(COMMA);

    /**
     * TODO token type set.
     */
    TokenSet IDENTIFIER_PUNCTUATOR_SET = TokenSet.create(DOT);

    /**
     * TODO token type set.
     */
    TokenSet ASSIGN_OPERATOR_SET = TokenSet.create(ASSIGN);

    /**
     * TODO token type set.
     */
    TokenSet LOGICAL_OPERATOR_SET = TokenSet.create(AND,
                                                    OR,
                                                    NOT);

    /**
     * TODO token type set.
     */
    TokenSet COMPARISON_OPERATOR_SET = TokenSet.create(LT,
                                                       LE,
                                                       EQ,
                                                       NE,
                                                       GE,
                                                       GT);

    /**
     * TODO token type set.
     */
    TokenSet BYTEWISE_OPERATOR_SET = TokenSet.create(BOR,
                                                     BAND,
                                                     BXOR,
                                                     TWIDDLE);

    /**
     * TODO token type set.
     */
    TokenSet ARITHMETIC_OPERATOR_SET = TokenSet.create(MUL,
                                                       DIV,
                                                       PLUS,
                                                       MINUS,
                                                       MOD);

    /**
     * TODO token type set.
     */
    TokenSet TERNARY_CONDITION_SET = TokenSet.create(TERN_IF,
                                                     COLON);

    /**
     * TODO token type set.
     */
    TokenSet DOLLAR_PREFIXED_IDENTIFIER_SET = TokenSet.create(DOLLAR);

    /**
     * TODO token type set.
     */
    TokenSet IDENTIFIER_SET = TokenSet.create(IDENTIFIER,
                                              NULL_LITERAL);

    /**
     * TODO token type set.
     */
    TokenSet NUMBER_SET = TokenSet.create(INTEGER_LITERAL,
                                          FLOAT_LITERAL);

    /**
     * TODO token type set.
     */
    TokenSet STRING_SET = TokenSet.create(STRING_LITERAL,
                                          QUOTEDIDENT);

    /**
     * TODO token type set.
     */
    TokenSet WHITE_SPACE_SET = TokenSet.create(WHITE_SPACE);

    /**
     * TODO token type set.
     */
    TokenSet COMMENT_SET = TokenSet.create(COMMENT);

    /**
     * TODO token type set.
     */
    TokenSet ERROR_SET = TokenSet.create(ERROR_ELEMENT);
}
