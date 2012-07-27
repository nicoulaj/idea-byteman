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
package net.nicoulaj.idea.byteman.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.JavaClassReferenceProvider;
import org.jetbrains.annotations.NotNull;


abstract class AbstractBytemanClassNameImpl extends BytemanPsiElementImpl {

    private static final JavaClassReferenceProvider CLASS_REFERENCE_PROVIDER = new JavaClassReferenceProvider();
    static {
        CLASS_REFERENCE_PROVIDER.setSoft(true);
        CLASS_REFERENCE_PROVIDER.setOption(JavaClassReferenceProvider.NOT_ENUM, true);
        CLASS_REFERENCE_PROVIDER.setOption(JavaClassReferenceProvider.NOT_INTERFACE, true);
    }

    public AbstractBytemanClassNameImpl(ASTNode node) {
        super(node);
    }

    @NotNull @Override public PsiReference[] getReferences() {
        return CLASS_REFERENCE_PROVIDER.getReferencesByElement(this);
    }
}
