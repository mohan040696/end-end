package com.air_asia_stepdefintion;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.resources.ExtentReporterNG;
import com.resources.GenericLibrary;

public class ListenersITest extends GenericLibrary implements ITestListener {

	
	ExtentReports extent = ExtentReporterNG.reportInit();
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Start");
		test = extent.createTest(result.getMethod().getMethodName());
		System.out.println("Report started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("failure");
		test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Suite Started");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Suite Ended");
		extent.flush();
	}

}
