package com.ReportingSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportingUtils {

    public static ExtentReports extentReports= new ExtentReports();
    public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();

    public static ExtentReports createReportInstance(String fileName, String reportName, String documentTitle){
        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Operating system",System.getProperty("os.name"));
        extentReports.setSystemInfo("OS Version",System.getProperty("os.version"));
        extentReports.setSystemInfo("OS Architecture",System.getProperty("os.arch"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("UserName",System.getProperty("user.name"));
        extentReports.setSystemInfo("Timezone",System.getProperty("user.timezone"));
        extentReports.setSystemInfo("User Location",System.getProperty("user.country"));
        return extentReports;
    }

    public static String getReportNameTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss a");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        String reportName = "TestReport" + formattedTime + ".html";
        return reportName;
    }

    public static void logPassDetails(String log){
        ReportingUtils.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));

    }

    public static void logFailureDetails(String log){
        ReportingUtils.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logInfoDetails(String log){
        ReportingUtils.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.BLACK));
    }

    public static void logWarningDetails(String log){
        ReportingUtils.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

}
