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

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Icons for the Byteman language support plugin.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class BytemanIcons {

    /**
     * The path to the Byteman icon.
     */
    public static final String BYTEMAN_ICON_PATH = "/net/nicoulaj/idea/byteman/byteman.png";

    /**
     * The Byteman {@link Icon}.
     */
    public static final Icon BYTEMAN_ICON = IconLoader.getIcon(BYTEMAN_ICON_PATH);
}
