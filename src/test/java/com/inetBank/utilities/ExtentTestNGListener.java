package com.inetBank.utilities;



import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class ExtentTestNGListener implements ITestListener, ISuiteListener 
{
	@Override
    public void onTestStart(ITestResult result) 
	{
        ExtentTest test = ExtentManager.getReporter().createTest(result.getMethod().getMethodName());
        ExtentManager.setExtentTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) 
    {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null)
        {
            test.log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) 
    {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) 
        {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null)
        {
            test.log(Status.SKIP, "Test Skipped");
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
    {
        // Not implemented
    }

    @Override
    public void onStart(ITestContext context) 
    {
        // Not implemented
    }

    @Override
    public void onFinish(ITestContext context) 
    {
        ExtentManager.getReporter().flush();
    }

}
