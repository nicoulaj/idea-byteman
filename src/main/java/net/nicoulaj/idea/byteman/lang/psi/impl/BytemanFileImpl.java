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

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import net.nicoulaj.idea.byteman.BytemanLanguage;
import net.nicoulaj.idea.byteman.file.BytemanFileType;
import net.nicoulaj.idea.byteman.lang.psi.BytemanFile;
import net.nicoulaj.idea.byteman.lang.psi.BytemanHelper;
import net.nicoulaj.idea.byteman.lang.psi.BytemanRule;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.psi.util.PsiTreeUtil.getChildOfType;
import static com.intellij.psi.util.PsiTreeUtil.getChildrenOfTypeAsList;

/**
 * Implementation of {@link BytemanFile}.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanFileImpl extends PsiFileBase implements BytemanFile {

    private CachedValue<List<BytemanRule>> rulesCache;
    private CachedValue<BytemanHelper> helperCache;

    /**
     * Build a new instance of {@link BytemanFileImpl}.
     *
     * @param viewProvider the {@link FileViewProvider} associated with this file.
     */
    public BytemanFileImpl(FileViewProvider viewProvider) {
        super(viewProvider, BytemanLanguage.INSTANCE);
    }

    /**
     * Get the file type for the file.
     *
     * @return {@link BytemanFileType#INSTANCE}
     */
    @NotNull
    public FileType getFileType() {
        return BytemanFileType.INSTANCE;
    }

    @Override
    public List<BytemanRule> getRules() {
        if (rulesCache == null)
            rulesCache = CachedValuesManager.getManager(getProject()).createCachedValue(new CachedValueProvider<List<BytemanRule>>() {
                @Override
                public Result<List<BytemanRule>> compute() {
                    return Result.create(getChildrenOfTypeAsList(BytemanFileImpl.this, BytemanRule.class), BytemanFileImpl.this);
                }
            }, false);
        return rulesCache.getValue();
    }

    @Override public BytemanHelper getHelper() {
        if (helperCache == null)
            helperCache = CachedValuesManager.getManager(getProject()).createCachedValue(new CachedValueProvider<BytemanHelper>() {
                @Override
                public Result<BytemanHelper> compute() {
                    return Result.create(getChildOfType(BytemanFileImpl.this, BytemanHelper.class), BytemanFileImpl.this);
                }
            }, false);
        return helperCache.getValue();
    }
}
