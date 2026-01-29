package com.ReportingSetup;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ReportSetup implements ITestListener{
    @Override
    public void onStart(ITestContext context) {
        String fileName=ReportingUtils.getReportNameTimeStamp();
        String fullReportPath=System.getProperty("user.dir")+"/target/reports/" + fileName;
        ReportingUtils.extentReports=ReportingUtils.createReportInstance(fullReportPath,"API Test Automation report","Test execution report");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (ReportingUtils.extentReports!=null)
            ReportingUtils.extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {

      ReportingUtils.logFailureDetails(result.getThrowable().getMessage());
      String stackTrace= Arrays.toString(result.getThrowable().getStackTrace());
      stackTrace=stackTrace.replaceAll(",","<br>");
      ReportingUtils.logFailureDetails(stackTrace);

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test =ReportingUtils.extentReports.createTest("TestName "+ result.getMethod().getMethodName());
        ReportingUtils.extentTest.set(test);
    }


}
