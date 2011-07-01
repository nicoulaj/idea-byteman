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

import com.intellij.lexer.Lexer;
import net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;

/**
 * Coverage test for {@link net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer}.
 * <p/>
 * Lexes some input files and compares tokens lengths sum with input document length.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
@RunWith(value = Parameterized.class)
public class BytemanLexerCoverageTest extends AbstractBytemanLexerTestCase {

    /**
     * Build a new instance of {@link BytemanLexerCoverageTest}.
     *
     * @param testFile the file to test
     */
    public BytemanLexerCoverageTest(File testFile) {
        super(testFile);
    }

    /**
     * Test the {@link #testFile} lexing coverage.
     *
     * @throws java.io.IOException
     */
    @Test
    public void doLexerTest() throws IOException {

        // Load the test file data.
        final String text = getTestFileData();

        // Process the data with a lexer.
        final Lexer lexer = new BytemanLexer();
        int count = 0;
        lexer.start(text);
        while (lexer.getTokenType() != null) {
            count += lexer.getBufferSequence().subSequence(lexer.getTokenStart(), lexer.getTokenEnd()).toString().length();
            lexer.advance();
        }

        // Compare results with the expected ones.
        assertEquals(text.length(), count);
    }
}
