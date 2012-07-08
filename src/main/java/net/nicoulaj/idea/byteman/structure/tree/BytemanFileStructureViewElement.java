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
package net.nicoulaj.idea.byteman.structure.tree;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import net.nicoulaj.idea.byteman.file.BytemanFileType;
import net.nicoulaj.idea.byteman.lang.psi.BytemanFile;
import net.nicoulaj.idea.byteman.lang.psi.BytemanRule;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BytemanFileStructureViewElement extends PsiTreeElementBase<BytemanFile> {

    public BytemanFileStructureViewElement(BytemanFile file) {
        super(file);
    }

    @Override public String getPresentableText() {
        return getElement().getName();
    }

    @Override public Icon getIcon(boolean open) {
        return BytemanFileType.INSTANCE.getIcon();
    }

    @NotNull @Override public Collection<StructureViewTreeElement> getChildrenBase() {
        final List<StructureViewTreeElement> rulesElements = new ArrayList<StructureViewTreeElement>();
        for (BytemanRule rule : getElement().getRules()) rulesElements.add(new BytemanRuleStructureViewElement(rule));
        return rulesElements;
    }
}
