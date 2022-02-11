package com.resources;
import com.aventstack.extentreports.Status;

public class Reports {
	
	
	public Reports fatal() {
		  log(Status.FAIL);
		  return this;
		}
//	public static String takeScreenshot;
//	public static ExtentReports report;
//	public static ExtentTest test;
//	public static GenericLibrary generic = new GenericLibrary();

	private void log(Status fail) {
		// TODO Auto-generated method stub
		
	}
	
//	public static  ExtentReports getInstance(String name) throws Exception {
//	if(report ==null) {
//		report = new ExtentReports(generic.loadProperties("ReportsIndividualTestPath")+"\\"+name+"_"+generic.currentDateAndTime()+".html");
//	}
//	return report;
//	}
//	public void startTest() {
//		test = getInstance()
//
//	}
//
//	public static void logStep(LogStatus sStatus, String stepname, String Actual, boolean isScreenShot) {
//		try {
//			File gb_Obj_directory;
//			DateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//			Date date = new Date();
//			String timestamp = dateformat.format(date).toString();
//			if (LogStatus.FAIL.equals(sStatus)) {
//				if (isScreenShot == true) {
//					gb_Obj_directory = new File(".");
//					String imgpath = generic.loadProperties("ScreenshotPath") + "\\" + "FailureScreenshots_"
//							+ timestamp + ".png";
//					takeScreenshot = generic.ScreenCapture(imgpath);
//					getTest().log(LogStatus.FAIL, stepname,
//							Actual + test.addScreenCapture(getImageRelativePath(new File(takeScreenshot))));
//				}
//			} else if (LogStatus.PASS.equals(sStatus)) {
//				if (isScreenShot == true) {
//					gb_Obj_directory = new File(".");
//					String imgpath = generic.loadProperties("ScreenshotPath") + "\\" + "PassedScreenshots_"
//							+ timestamp + ".png";
//					takeScreenshot = generic.ScreenCapture(imgpath);
//					getTest().log(LogStatus.FAIL, stepname,
//							Actual + test.addScreenCapture(getImageRelativePath(new File(takeScreenshot))));
//				}
//			} else if (LogStatus.INFO.equals(sStatus)) {
//				if (isScreenShot == true) {
//					gb_Obj_directory = new File(".");
//					gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
//					String imgpath = generic.loadProperties("ScreenshotPath") + "\\" + "InfoScreenshots_"
//							+ timestamp + ".png";
//					takeScreenshot = generic.ScreenCapture(imgpath);
//					getTest().log(LogStatus.FAIL, stepname,
//							Actual + test.addScreenCapture(getImageRelativePath(new File(takeScreenshot))));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
