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
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import net.nicoulaj.idea.byteman.lang.BytemanTypes;
import net.nicoulaj.idea.byteman.lang.psi.BytemanPsiNamedElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;


public class BytemanRuleBase extends BytemanPsiElementImpl implements BytemanPsiNamedElement {

    public BytemanRuleBase(ASTNode node) {
        super(node);
    }

    @Override public String getName() {
        return getNameIdentifier().getText();
    }

    @Override public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return null; // TODO Not implemented
    }

    @Override public PsiElement getNameIdentifier() {
        return findChildByType(BytemanTypes.RULE_NAME);
    }
}
