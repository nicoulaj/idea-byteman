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

import com.intellij.psi.tree.IElementType;
import net.nicoulaj.idea.byteman.file.BytemanFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * {@link IElementType} implementation for Byteman.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanElementType extends IElementType {

    /**
     * Build a new instance of {@link BytemanElementType}.
     *
     * @param debugName the name of the element type, used for debugging purposes.
     */
    public BytemanElementType(@NotNull @NonNls String debugName) {
        super(debugName, BytemanFileType.LANGUAGE);
    }

    /**
     * Build in {@link String} representation of this {@link BytemanElementType}.
     *
     * @return the String representation based on {@link com.intellij.psi.tree.IElementType#toString()}.
     */
    @Override
    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return MessageFormat.format("Byteman:{0}", super.toString());
    }
}
