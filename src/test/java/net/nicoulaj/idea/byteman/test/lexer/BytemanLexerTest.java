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
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.tree.IElementType;
import net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer;
import org.jetbrains.annotations.NonNls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;

/**
 * Black box test for {@link net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer}.
 * <p/>
 * Lexes some input files and compares result with expected output.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
@RunWith(value = Parameterized.class)
public class BytemanLexerTest extends AbstractBytemanLexerTestCase {

    /**
     * The files containing the expected lexer results are named after the test file file name + this suffix.
     */
    @NonNls
    public static final String TEST_FILES_TOKENS_FILE_EXT = ".lexer.csv";

    /**
     * Build a new instance of {@link BytemanLexerTest}.
     *
     * @param testFile the file to test
     */
    public BytemanLexerTest(File testFile) {
        super(testFile);
    }

    /**
     * Test the {@link #testFile} lexing.
     *
     * @throws java.io.IOException
     */
    @Test
    public void doLexerTest() throws IOException {

        // Load the test file data.
        final String text = getTestFileData();

        // Process the data with a lexer.
        final Lexer lexer = new BytemanLexer();
        String result = "";
        String chunk = "";
        IElementType tokenType;
        String tokenText;
        String tokenTypeName = "";
        lexer.start(text);
        while (true) {
            tokenType = lexer.getTokenType();
            tokenText = StringUtil.replace(lexer.getBufferSequence().subSequence(lexer.getTokenStart(), lexer.getTokenEnd()).toString(), "\n", "\\n");
            if (tokenType != null && tokenTypeName.equals(tokenType.toString())) {
                chunk += tokenText;
            } else {
                if (tokenTypeName.length() > 0) {
                    result += tokenTypeName + ";\"" + chunk + "\"\n";
                }
                if (tokenType != null) {
                    tokenTypeName = tokenType.toString();
                    chunk = tokenText;
                }
            }
            if (tokenType == null) {
                break;
            }
            lexer.advance();
        }

//        Uncomment to generate all lexer result files
//        FileUtil.writeToFile(new File(getTestFile().getPath() + TEST_FILES_TOKENS_FILE_EXT), result);

        // Compare results with the expected ones.
        assertSameLinesWithFile(getTestFile().getPath() + TEST_FILES_TOKENS_FILE_EXT, result);
    }
}
