package com.testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
    ExtentReports report=ExtentReporterNG.getReportObject();
	@Override
	public void onTestStart(ITestResult result) {
     test=report.createTest(result.getMethod().getMethodName());
     
	}

	@Override
	public void onTestSuccess(ITestResult result) {
    test.log(Status.PASS, "test is passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
    test.fail(result.getThrowable());
    try {
		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace(); 
	}
    String filepath=getScreenshot(result.getMethod().getMethodName(),driver);
    test.addScreenCaptureFromPath(filepath);
    

	}

	@Override
	public void onTestSkipped(ITestResult result) {
     test.log(Status.SKIP, "test is skippe");

	}

	@Override
	public void onFinish(ITestContext context) {
     report.flush();

	}

}
