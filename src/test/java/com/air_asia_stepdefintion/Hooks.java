package com.air_asia_stepdefintion;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.resources.GenericLibrary;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
public class Hooks extends GenericLibrary {
	
	public static GenericLibrary generic = new GenericLibrary();
//	public static ExtentReports extent;
//	public ExtentTest test;
	

	@Before(order = 0)
	public void initialize(Scenario sc) throws Exception {
//		String name = sc.getName();
//		String location = GenericLibrary.loadProperties("ReportsIndividualTestPath") + sc.getName()
//				+ generic.currentDateAndTime() + ".html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(location);
//		reporter.config().setReportName(name);
//		reporter.config().setDocumentTitle("TEST RESULTS");
//
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
	}
	@Before(order =1)
	public void startDriver(Scenario sc) throws InterruptedException {
//		ExtentTest test = extent.createTest(sc.getName());
//		test.log(Status.PASS, "testcasepass");
		GenericLibrary.driverInit();
	}
	
	@After
	public void tearDown() {
		closeBrowser();
		//extent.flush();
	}
	@AfterStep
	public void addScreenShot(Scenario sc) throws Exception {	
		//ExtentCucumberAdapter.addTestStepLog(Message);
		if (sc.isFailed()) {
			String currentDateAndTime = generic.currentDateAndTime();
			 final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			  sc.attach(screenshot, "image/png", "Failed '"+currentDateAndTime+"'");
		} else {
			String currentDateAndTime = generic.currentDateAndTime();
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			  sc.attach(screenshot, "image/png", "Passed '"+currentDateAndTime+"'");
		}
	}
	}

