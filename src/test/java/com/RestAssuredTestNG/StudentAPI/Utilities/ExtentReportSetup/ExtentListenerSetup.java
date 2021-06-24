package com.RestAssuredTestNG.StudentAPI.Utilities.ExtentReportSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListenerSetup implements ITestListener {

    ExtentReports extentReports=new ExtentReports();
    ExtentSparkReporter sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/extent.html");
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Rest Assured Automation Report");
        sparkReporter.config().setReportName("TestNG Student API Report");
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("JDK Version","1.8.0_261");
        extentReports.setSystemInfo("Maven","3.6.3");
        extentReports.setSystemInfo("Rest Assured version","4.4.0");
        extentReports.setSystemInfo("Project Name","Student Database API");
        extentReports.setSystemInfo("Host name","localhost");
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
        test.fail("TEST ERROR IS "+result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test=extentReports.createTest(result.getName());
        test.skip(result.getName()+" HAS BEEN SKIPPED");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
