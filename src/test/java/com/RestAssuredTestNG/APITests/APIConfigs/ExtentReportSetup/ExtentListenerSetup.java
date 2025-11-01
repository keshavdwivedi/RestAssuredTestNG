package com.RestAssuredTestNG.APITests.APIConfigs.ExtentReportSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ExtentListenerSetup implements ITestListener {

    ExtentReports extentReports=new ExtentReports();
    ExtentSparkReporter sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/extent.html");
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Rest Assured Automation Report");
        sparkReporter.config().setReportName("TestNG Extent API Report");
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("JDK Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("Maven","3.9.9");
        extentReports.setSystemInfo("Rest Assured version","5.5.0");
        extentReports.setSystemInfo("Project Name","Stripe API Tests");
        extentReports.setSystemInfo("User name",System.getProperty("user.name"));
        extentReports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        extentReports.setSystemInfo("User Location", System.getProperty("user.country"));
        extentReports.setSystemInfo("OS name", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS version", System.getProperty("os.version"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        test=extentReports.createTest(result.getName());
        test.info(result.getName()+" HAS BEEN STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test=extentReports.createTest(result.getName());
        test.pass("TESTCASE SUCCESSFULLY COMPLETED IS "+result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        test=extentReports.createTest(result.getName());
        test.fail("TEST CASE FAILED IS " + result.getName());
        String stackTrace= Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace=stackTrace.replaceAll(",","<br>");
        test.fail("TEST ERROR IS "+result.getThrowable().getMessage());
        test.fail("ERROR DETAILS ARE "+stackTrace);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test=extentReports.createTest(result.getName());
        test.skip(result.getName()+" HAS BEEN SKIPPED");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReports!=null){
            extentReports.flush();
        }
    }
}
