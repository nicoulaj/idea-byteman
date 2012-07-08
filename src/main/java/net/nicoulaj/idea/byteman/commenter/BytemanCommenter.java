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
package net.nicoulaj.idea.byteman.commenter;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

/**
 * Defines the support for "Comment with Line Comment" and "Comment with Block Comment" actions in the Byteman language.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanCommenter implements Commenter {

    /**
     * The prefix for line comments.
     *
     * @see {@link #getLineCommentPrefix()}
     */
    public static final String LINE_COMMENT_PREFIX = "#";

    /**
     * Returns the string which prefixes a line comment in the language.
     *
     * @return {@link #LINE_COMMENT_PREFIX}
     */
    @Nullable
    public String getLineCommentPrefix() {
        return LINE_COMMENT_PREFIX;
    }

    /**
     * Returns the string which marks the beginning of a block comment in the language.
     *
     * @return {@code null} as the language does not support block comments.
     */
    @Nullable
    public String getBlockCommentPrefix() {
        return null;
    }

    /**
     * Returns the string which marks the end of a block comment in the language.
     *
     * @return {@code null} as the language does not support block comments.
     */
    @Nullable
    public String getBlockCommentSuffix() {
        return null;
    }

    /**
     * Returns the string which marks the commented beginning of a block comment in the language.
     *
     * @return {@code null} as the language does not support block comments.
     */
    @Nullable
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    /**
     * Returns the string which marks the commented end of a block comment in the language.
     *
     * @return {@code null} as the language does not support block comments.
     */
    @Nullable
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
