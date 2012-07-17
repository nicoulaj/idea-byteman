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
package net.nicoulaj.idea.byteman.test.parser;

import com.intellij.testFramework.ParsingTestCase;
import net.nicoulaj.idea.byteman.lang.parser.BytemanParserDefinition;
import org.junit.Test;

import static net.nicoulaj.idea.byteman.test.TestUtils.TEST_SCRIPTS_DIR;
import static net.nicoulaj.idea.byteman.test.TestUtils.TEST_SCRIPTS_EXTENSION;

/**
 * Tests for the Byteman parser.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanParserTest extends ParsingTestCase {

    public BytemanParserTest() {
        super("", TEST_SCRIPTS_EXTENSION, new BytemanParserDefinition());
    }

    @Override protected String getTestDataPath() {
        return TEST_SCRIPTS_DIR;
    }

    @Test public void testClassLoadMonitor() {doTest(true);}

    @Test public void testFileMonitor() {doTest(true);}

    @Test public void testFinalizeMonitor() {doTest(true);}

    @Test public void testJVMMBeanStats() {doTest(true);}

    @Test public void testNGUnitTest() {doTest(true);}

    @Test public void testNGUnitTestTestOne() {doTest(true);}

    @Test public void testNGUnitTestThree() {doTest(true);}

    @Test public void testNGUnitTestThreeExtra() {doTest(true);}

    @Test public void testNGUnitTestTwo() {doTest(true);}

    @Test public void testPeriodicStats() {doTest(true);}

    @Test public void testSocketMonitor() {doTest(true);}

    @Test public void testTestAll() {doTest(true);}

    @Test public void testTestArithmetic() {doTest(true);}

    @Test public void testTestArray() {doTest(true);}

    @Test public void testTestAssign() {doTest(true);}

    @Test public void testTestCall() {doTest(true);}

    @Test public void testTestCallerMatches() {doTest(true);}

    @Test public void testTestComparison() {doTest(true);}

    @Test public void testTestEmptySignature() {doTest(true);}

    @Test public void testTestEnclosedSynchronizationPropagation() {doTest(true);}

    @Test public void testTestEntry() {doTest(true);}

    @Test public void testTestExit() {doTest(true);}

    @Test public void testTestField() {doTest(true);}

    @Test public void testTestInterfaceHierarchy() {doTest(true);}

    @Test public void testTestInterfaceInjection() {doTest(true);}

    @Test public void testTestInvokeParamBinding() {doTest(true);}

    @Test public void testTestLine() {doTest(true);}

    @Test public void testTestLogical() {doTest(true);}

    @Test public void testTestMethodActualAgainstFormal() {doTest(true);}

    @Test public void testTestMethod() {doTest(true);}

    @Test public void testTestMethodClauseReturnType() {doTest(true);}

    @Test public void testTestMethodParamName() {doTest(true);}

    @Test public void testTestMultiMethodMatch() {doTest(true);}

    @Test public void testTestNew() {doTest(true);}

    @Test public void testTestOverridingInjection() {doTest(true);}

    @Test public void testTestOverridingInterfaceInjection() {doTest(true);}

    @Test public void testTestParamBinding() {doTest(true);}

    @Test public void testTestPromotePrimitiveToObject() {doTest(true);}

    @Test public void testTestReadWrite() {doTest(true);}

    @Test public void testTestReadWriteParams() {doTest(true);}

    @Test public void testTestRecursiveTriggers() {doTest(true);}

    @Test public void testTestReturnBindingAssignment() {doTest(true);}

    @Test public void testTestReturnBinding() {doTest(true);}

    @Test public void testTestStackTrace() {doTest(true);}

    @Test public void testTestSynch() {doTest(true);}

    @Test public void testTestThrowAction() {doTest(true);}

    @Test public void testTestThrowBinding() {doTest(true);}

    @Test public void testTestThrow() {doTest(true);}

    @Test public void testTestWaitAfterSignalWakeMustMeet() {doTest(true);}

    @Test public void testThreadMonitor() {doTest(true);}

    @Test public void testTXMBeanStats() {doTest(true);}

    @Test public void testUnitTest() {doTest(true);}

    @Test public void testUnitTestTestOne() {doTest(true);}

    @Test public void testUnitTestThree() {doTest(true);}

    @Test public void testUnitTestThreeExtra() {doTest(true);}

    @Test public void testUnitTestTwo() {doTest(true);}
}
