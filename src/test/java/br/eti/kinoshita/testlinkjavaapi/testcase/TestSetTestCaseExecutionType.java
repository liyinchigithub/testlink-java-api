/*
 * The MIT License
 *
 * Copyright (c) 2010 Bruno P. Kinoshita http://www.kinoshita.eti.br
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.eti.kinoshita.testlinkjavaapi.testcase;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestSetTestCaseExecutionType extends BaseTest {

    @DataProvider(name = "testCaseData")
    public Object[][] createData() {
        return new Object[][] { { 1, 4, null, 1, ExecutionType.AUTOMATED } };
    }

    @Test(dataProvider = "testCaseData")
    public void testSetTestCaseExecutionType(Integer testProjectId, Integer testCaseId, Integer testCaseExternalId,
            Integer versionNumber, ExecutionType executionType) {
        this.loadXMLRPCMockData("tl.setTestCaseExecutionType.xml");

        Map<String, Object> response = null;
        try {
            response = this.api.setTestCaseExecutionType(testProjectId, testCaseId, testCaseExternalId, versionNumber,
                    executionType);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(response);

        Assert.assertTrue(Integer.parseInt(response.get("testprojectid").toString()) > 0);
        Assert.assertTrue(Integer.parseInt(response.get("testcaseid").toString()) > 0);
    }

}
