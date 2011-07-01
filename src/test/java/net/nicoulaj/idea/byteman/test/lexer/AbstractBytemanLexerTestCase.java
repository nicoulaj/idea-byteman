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
package net.nicoulaj.idea.byteman.test.lexer;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.testFramework.UsefulTestCase;
import net.nicoulaj.idea.byteman.test.TestUtils;
import org.jetbrains.annotations.NonNls;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Base class for testing {@link net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer} against all available test input documents.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public abstract class AbstractBytemanLexerTestCase extends UsefulTestCase {

    /**
     * The path to the data used for this test.
     */
    @NonNls
    public static final String LEXER_TEST_RESOURCES_DIR = TestUtils.TEST_RESOURCES_DIR + "/lexer";

    /**
     * The pattern used for catching input test data files.
     */
    public static final Pattern TEST_FILES_PATTERN = Pattern.compile(".+.btm");

    /**
     * The file to test.
     */
    private File testFile;

    /**
     * Build a new instance of {@link AbstractBytemanLexerTestCase}.
     *
     * @param testFile the file to test
     */
    protected AbstractBytemanLexerTestCase(File testFile) {
        this.testFile = testFile;
    }

    /**
     * Generate the data to use to instantiate this test.
     *
     * @return a {@link Collection} of parameters to pass to {@link #AbstractBytemanLexerTestCase(java.io.File)} implementations constructor.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> getTestFiles() {
        final List<File> dataFiles = FileUtil.findFilesByMask(TEST_FILES_PATTERN, new File(LEXER_TEST_RESOURCES_DIR));
        Collections.sort(dataFiles);
        final Collection<Object[]> res = new ArrayList<Object[]>(dataFiles.size());
        for (File file : dataFiles) {
            res.add(new Object[]{file});
        }
        return res;
    }

    /**
     * Get the file to run the test against.
     *
     * @return a {@link File} from {@link #LEXER_TEST_RESOURCES_DIR}
     */
    protected File getTestFile() {
        return testFile;
    }

    /**
     * Get the test file data as a {@link String}.
     *
     * @return the test file data in a single {@link String}
     * @throws IOException if the data could not be loaded
     */
    protected String getTestFileData() throws IOException {
        return new String(FileUtil.loadFileText(testFile));
    }

    /**
     * Actual implementation of the test to run against {@link #testFile}.
     *
     * @throws Exception if an error occured while running the test
     */
    abstract public void doLexerTest() throws Exception;
}
