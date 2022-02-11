package com.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class  ExtentReporterNG extends GenericLibrary{
	static ExtentReports extent;
	
	public static ExtentReports reportInit() {
		String path = System.getProperty("user.dir") + "\\Reports\\Extent.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation");
		reporter.config().setDocumentTitle("Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	

}
