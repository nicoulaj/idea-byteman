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
package net.nicoulaj.idea.byteman.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link FileTypeFactory} responsible for registering {@link BytemanFileType} with the system.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanFileTypeFactory extends FileTypeFactory {

    /**
     * Register extensions with the file type declared for Byteman.
     *
     * @param fileTypeConsumer the {@link FileTypeConsumer} to register extensions with.
     * @see BytemanFileType#DEFAULT_ASSOCIATED_EXTENSIONS
     */
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        for (int i = 0; i < BytemanFileType.DEFAULT_ASSOCIATED_EXTENSIONS.length; i++) {
            fileTypeConsumer.consume(BytemanFileType.INSTANCE, BytemanFileType.DEFAULT_ASSOCIATED_EXTENSIONS[i]);
        }
    }
}
