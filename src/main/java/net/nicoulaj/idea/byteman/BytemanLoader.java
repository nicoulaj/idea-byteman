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
package net.nicoulaj.idea.byteman;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import net.nicoulaj.idea.byteman.file.BytemanFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * {@link ApplicationComponent} loading Byteman support.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanLoader implements ApplicationComponent {

    /**
     * The component name used by {@link #getComponentName()}.
     */
    @NonNls
    protected static final String COMPONENT_NAME = "byteman.support.loader";

    /**
     * Get the unique name of this component.
     *
     * @return {@link #COMPONENT_NAME}
     */
    @NotNull @NonNls
    public String getComponentName() {
        return COMPONENT_NAME;
    }

    /**
     * Initialize the component.
     * <p/>
     * Registers Byteman file types.
     */
    public void initComponent() {
        FileTypeManager.getInstance().registerFileType(BytemanFileType.INSTANCE, BytemanFileType.DEFAULT_ASSOCIATED_EXTENSIONS);
    }

    /**
     * Dispose the component.
     */
    public void disposeComponent() {
    }
}
