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
package net.nicoulaj.idea.byteman.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import net.nicoulaj.idea.byteman.BytemanLanguage;
import net.nicoulaj.idea.byteman.BytemanResources;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * The {@link LanguageFileType} for Byteman files.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanFileType extends LanguageFileType {

    /** The {@link BytemanFileType} instance. */
    public static final BytemanFileType INSTANCE = new BytemanFileType();

    /** The extensions associated by default with this {@link com.intellij.openapi.fileTypes.FileType}. */
    @NonNls
    public static final String[] DEFAULT_ASSOCIATED_EXTENSIONS = {"btm"};

    /** Build a new instance of {@link BytemanFileType}. */
    public BytemanFileType() {
        super(BytemanLanguage.INSTANCE);
    }

    /**
     * Get the name associated with this {@link com.intellij.openapi.fileTypes.FileType}.
     *
     * @return the name as defined by {@link net.nicoulaj.idea.byteman.BytemanResources.Bundle}.
     */
    @NotNull
    public String getName() {
        return BytemanResources.Bundle.message("byteman.filetype.name");
    }

    /**
     * Get the description associated with this {@link com.intellij.openapi.fileTypes.FileType}.
     *
     * @return the description as defined by {@link net.nicoulaj.idea.byteman.BytemanResources.Bundle}.
     */
    @NotNull
    public String getDescription() {
        return BytemanResources.Bundle.message("byteman.filetype.description");
    }

    /**
     * Get the default extension for this {@link com.intellij.openapi.fileTypes.FileType}.
     *
     * @return the first entry of {@link #DEFAULT_ASSOCIATED_EXTENSIONS}.
     */
    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_ASSOCIATED_EXTENSIONS[0];
    }

    /**
     * Get the icon associated with this {@link com.intellij.openapi.fileTypes.FileType}.
     *
     * @return {@link net.nicoulaj.idea.byteman.BytemanResources.Graphics#BYTEMAN_FILE_ICON_16}.
     */
    public Icon getIcon() {
        return BytemanResources.Graphics.BYTEMAN_FILE_ICON_16;
    }
}
