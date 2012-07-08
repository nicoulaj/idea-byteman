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
package net.nicoulaj.idea.byteman.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import net.nicoulaj.idea.byteman.lang.psi.BytemanFile;
import net.nicoulaj.idea.byteman.lang.psi.BytemanRule;
import net.nicoulaj.idea.byteman.structure.tree.BytemanFileStructureViewElement;
import org.jetbrains.annotations.NotNull;

public class BytemanStructureViewModel extends TextEditorBasedStructureViewModel {

    private final Filter[] filters = new Filter[]{};
    private final Grouper[] groupers = new Grouper[]{};

    public BytemanStructureViewModel(final BytemanFile file) {
        super(file);
    }

    @NotNull
    public StructureViewTreeElement getRoot() {
        return new BytemanFileStructureViewElement((BytemanFile) getPsiFile());
    }

    @NotNull
    public Grouper[] getGroupers() {
        return groupers;
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }

    @NotNull
    public Filter[] getFilters() {
        return filters;
    }

    @NotNull
    protected Class[] getSuitableClasses() {
        return new Class[]{BytemanRule.class};
    }
}
