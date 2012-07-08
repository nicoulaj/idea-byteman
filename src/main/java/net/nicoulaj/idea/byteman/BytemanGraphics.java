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

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Graphic resources for the Byteman language support plugin.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanGraphics {

    /** Path to Byteman graphic resources. */
    public static final String BYTEMAN_GRAPHICS_PATH = "/net/nicoulaj/idea/byteman/graphics/";

    /** The Byteman {@link Icon} (16x16px). */
    public static final Icon BYTEMAN_ICON_16 = IconLoader.getIcon(BYTEMAN_GRAPHICS_PATH + "byteman-logo-16.png");

    /** The Byteman {@link Icon} (24x24px). */
    public static final Icon BYTEMAN_ICON_24 = IconLoader.getIcon(BYTEMAN_GRAPHICS_PATH + "byteman-logo-24.png");

    /** The Byteman {@link Icon} (32x32px). */
    public static final Icon BYTEMAN_ICON_32 = IconLoader.getIcon(BYTEMAN_GRAPHICS_PATH + "byteman-logo-32.png");

    /** The Byteman {@link Icon} (64x64px). */
    public static final Icon BYTEMAN_ICON_64 = IconLoader.getIcon(BYTEMAN_GRAPHICS_PATH + "byteman-logo-64.png");

    /** The Byteman {@link Icon} (256x256px). */
    public static final Icon BYTEMAN_ICON_256 = IconLoader.getIcon(BYTEMAN_GRAPHICS_PATH + "byteman-logo-256.png");

    /** The Byteman file {@link Icon} (16x16px). */
    public static final Icon BYTEMAN_FILE_ICON_16 = IconLoader.getIcon(BYTEMAN_GRAPHICS_PATH + "byteman-file-16.png");
}
