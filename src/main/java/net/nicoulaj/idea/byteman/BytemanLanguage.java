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
package net.nicoulaj.idea.byteman;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NonNls;

/**
 * Byteman {@link Language} definition.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanLanguage extends Language {

    /** The Byteman language name. */
    @NonNls
    private static final String NAME = "Byteman";

    /** The {@link BytemanLanguage} instance. */
    public static final BytemanLanguage INSTANCE = new BytemanLanguage();

    /** Build a new instance of {@link BytemanLanguage}. */
    private BytemanLanguage() {
        super(NAME);
    }
}
