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
package net.nicoulaj.idea.byteman.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import net.nicoulaj.idea.byteman.lang.psi.api.BytemanPsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Base implementation of {@link BytemanPsiElement}.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class BytemanPsiElementImpl extends ASTWrapperPsiElement implements BytemanPsiElement {

    /**
     * Build a new instance of {@link BytemanPsiElementImpl}.
     *
     * @param node the AST node.
     */
    public BytemanPsiElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
