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

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import static net.nicoulaj.idea.byteman.BytemanResources.Bundle.message;

/**
 * Base implementation for Byteman local inspections.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public abstract class AbstractBytemanLocalInspection extends LocalInspectionTool {

    /**
     * Get the inspection group display name.
     *
     * @return a non-null {@link String}
     */
    @Nls @NotNull @Override
    public String getGroupDisplayName() {
        return message("byteman.inspections.group.name");
    }

    /**
     * Get the inspection short name.
     *
     * @return the inspection class simple name.
     */
    @NotNull
    @Override
    public String getShortName() {
        return getClass().getSimpleName();
    }

    /**
     * Whether the inspection hsould be enabled by default.
     *
     * @return {@code true}
     */
    public boolean isEnabledByDefault() {
        return true;
    }

    /**
     * Report problems at file level.
     *
     * @param file       to check.
     * @param manager    InspectionManager to ask for ProblemDescriptor's from.
     * @param isOnTheFly true if called during on the fly editor highlighting. Called from Inspect Code action otherwise.
     * @return <code>null</code> if no problems found.
     * @see #checkFile(com.intellij.psi.PsiFile, com.intellij.codeInspection.ProblemsHolder, boolean)
     */
    public ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        final ProblemsHolder problemsHolder = new ProblemsHolder(manager, file, isOnTheFly);
        checkFile(file, problemsHolder, isOnTheFly);
        return problemsHolder.getResultsArray();
    }

    /**
     * Report problems at file level.
     *
     * @param file           to check.
     * @param problemsHolder the holder to report problems to.
     * @param isOnTheFly     true if called during on the fly editor highlighting. Called from Inspect Code action otherwise.
     */
    abstract protected void checkFile(@NotNull PsiFile file, @NotNull ProblemsHolder problemsHolder, boolean isOnTheFly);
}
