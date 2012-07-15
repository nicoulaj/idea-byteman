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
package net.nicoulaj.idea.byteman.lang.parser;

import com.intellij.lang.PsiBuilder;

/**
 * Static helper methods for generated {@link BytemanParser}.
 *
 * @see <code>Byteman.bnf</code>
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanParserUtil extends GeneratedParserUtilBase {

    /**
     * Parse {@link net.nicoulaj.idea.byteman.lang.psi.BytemanFile} root node.
     *
     * @param builder the {@link PsiBuilder} in use
     * @param level the nest level
     * @param parser the {@link Parser} instance
     * @return true if parsing succeded
     */
    public static boolean parseScript(PsiBuilder builder, int level, Parser parser) {
        return parseAsTree(ErrorState.get(builder), builder, level, DUMMY_BLOCK, true, parser, TRUE_CONDITION);
    }
}
