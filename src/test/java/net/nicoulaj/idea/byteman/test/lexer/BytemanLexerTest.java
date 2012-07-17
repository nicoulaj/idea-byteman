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
package net.nicoulaj.idea.byteman.test.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LexerTestCase;
import net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer;
import org.junit.Test;

import static net.nicoulaj.idea.byteman.test.TestUtils.TEST_SCRIPTS_DIR;
import static net.nicoulaj.idea.byteman.test.TestUtils.TEST_SCRIPTS_EXTENSION;

/**
 * Black box test for {@link net.nicoulaj.idea.byteman.lang.lexer.BytemanLexer}.
 * <p/>
 * Lexes some input files and compares result with expected output.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanLexerTest extends LexerTestCase {

    @Override protected Lexer createLexer() {
        return new BytemanLexer();
    }

    @Override protected String getDirPath() {
        return TEST_SCRIPTS_DIR;
    }

    @Test public void testClassLoadMonitor() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testFileMonitor() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testFinalizeMonitor() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testJVMMBeanStats() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testNGUnitTest() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testNGUnitTestTestOne() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testNGUnitTestThree() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testNGUnitTestThreeExtra() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testNGUnitTestTwo() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testPeriodicStats() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testSocketMonitor() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestAll() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestArithmetic() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestArray() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestAssign() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestCall() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestCallerMatches() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestComparison() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestEmptySignature() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestEnclosedSynchronizationPropagation() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestEntry() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestExit() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestField() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestInterfaceHierarchy() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestInterfaceInjection() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestInvokeParamBinding() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestLine() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestLogical() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestMethodActualAgainstFormal() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestMethod() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestMethodClauseReturnType() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestMethodParamName() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestMultiMethodMatch() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestNew() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestOverridingInjection() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestOverridingInterfaceInjection() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestParamBinding() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestPromotePrimitiveToObject() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestReadWrite() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestReadWriteParams() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestRecursiveTriggers() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestReturnBindingAssignment() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestReturnBinding() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestStackTrace() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestSynch() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestThrowAction() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestThrowBinding() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestThrow() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTestWaitAfterSignalWakeMustMeet() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testThreadMonitor() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testTXMBeanStats() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testUnitTest() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testUnitTestTestOne() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testUnitTestThree() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testUnitTestThreeExtra() {doFileTest(TEST_SCRIPTS_EXTENSION);}

    @Test public void testUnitTestTwo() {doFileTest(TEST_SCRIPTS_EXTENSION);}
}
