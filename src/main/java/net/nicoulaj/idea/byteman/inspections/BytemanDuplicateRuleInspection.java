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

package net.nicoulaj.idea.byteman.inspections;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.MultiMap;
import net.nicoulaj.idea.byteman.lang.psi.BytemanFile;
import net.nicoulaj.idea.byteman.lang.psi.BytemanRule;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static net.nicoulaj.idea.byteman.BytemanResources.Bundle.message;

/**
 * An inspection that detects duplicate rule names in a script.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanDuplicateRuleInspection extends AbstractBytemanLocalInspection {

    /**
     * Get the inspection display name.
     *
     * @return a non-null {@link String}
     */
    @Nls @NotNull @Override
    public String getDisplayName() {
        return message("byteman.inspections.duplicate-rule.name");
    }

    /** {@inheritDoc} */
    protected void checkFile(@NotNull PsiFile file, @NotNull ProblemsHolder problemsHolder, boolean isOnTheFly) {
        if (!(file instanceof BytemanFile)) return;

        // Build map of rules by name
        final MultiMap<String, BytemanRule> map = new MultiMap<String, BytemanRule>();
        for (final BytemanRule rule : ((BytemanFile) file).getRules())
            map.putValue(rule.getName(), rule);

        // Look for duplicates
        for (String name : map.keySet()) {
            final Collection<BytemanRule> rules = map.get(name);
            if (rules.size() > 1)
                for (final BytemanRule rule : rules)
                    problemsHolder.registerProblem(rule.getNameIdentifier(), message("byteman.inspections.duplicate-rule.message", name));
        }
    }
}
