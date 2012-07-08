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
package net.nicoulaj.idea.byteman.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import net.nicoulaj.idea.byteman.lang.BytemanTypes;
import net.nicoulaj.idea.byteman.lang.psi.BytemanFile;
import net.nicoulaj.idea.byteman.lang.psi.BytemanRule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link com.intellij.lang.folding.FoldingBuilder} implementation for Byteman.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanFoldingBuilder extends FoldingBuilderEx {

    /**
     * Builds the folding regions for the specified node in the AST tree and its children.
     *
     * @param root     the element for which folding is requested.
     * @param document the document for which folding is built.
     * @param quick    whether the result should be provided as soon as possible.
     * @return the array of folding descriptors.
     */
    @NotNull @Override public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        final List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        if (root instanceof BytemanFile)
            // FIXME Use a visitor
            for (BytemanRule rule : PsiTreeUtil.getChildrenOfTypeAsList(root, BytemanRule.class))
                descriptors.add(new FoldingDescriptor(rule, rule.getTextRange()));
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    /**
     * Returns the text which is displayed in the editor for the folding region related to the
     * specified node when the folding region is collapsed.
     *
     * @param astNode the node for which the placeholder text is requested.
     * @return the placeholder text.
     */
    @Override public String getPlaceholderText(@NotNull ASTNode astNode) {
        if (BytemanTypes.RULE == astNode.getElementType())
            return String.format("RULE %s...", ((BytemanRule) astNode.getPsi()).getName());
        return null;
    }

    /**
     * Returns the default collapsed state for the folding region related to the specified node.
     *
     * @param astNode the node for which the collapsed state is requested.
     * @return true if the region is collapsed by default, false otherwise.
     */
    @Override public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }
}
