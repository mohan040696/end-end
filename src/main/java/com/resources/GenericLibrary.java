package com.resources;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericLibrary extends Reports {

	public static WebDriver driver = null;
	public static Properties prop;
	public static FileReader reader;
	public static String homewindow = null;
	public static Logger log = LogManager.getLogger(GenericLibrary.class.getName());
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static String key;

	String curentdate = null;

	/**
	 * @author Mohan
	 * @param browser
	 * @return
	 * @throws InterruptedException 
	 *
	 */
	
	public static WebDriver driverInit() throws InterruptedException {
		String browser = loadProperties("browser");
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("disable-infobars");// disable the "Chrome contolled by automation software
			chromeoptions.addArguments("--incognito");// open new incognito browser
			chromeoptions.addArguments("start-maximized");// its open the browser maximized
			chromeoptions.addArguments("--diable-web-security");// disable the web security option
			chromeoptions.addArguments("--allow-running-insecure-content");// allowing the insecure application on
																			// chrome
			chromeoptions.addArguments("--no-sandbox");// not allowing third party cookies
			chromeoptions.addArguments("--disable-gpu");// disable grapics to reduce battary usage and improveing speed
			chromeoptions.addArguments("--dns-prefetch-disable");// its avoid to copy duplicates for later usage
			chromeoptions.addArguments("enable-automation");

			driver = new ChromeDriver(chromeoptions);
			Thread.sleep(10000);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			Thread.sleep(10000);
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			Thread.sleep(10000);
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Thread.sleep(10000);
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	/**
	 * @author Mohan
	 * @param browser loading properties from property file
	 * @return
	 *
	 */
	
	public static String loadProperties(String data) {
		String value = null;
		try {
			prop = new Properties();
			prop.load(new FileReader(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\resources\\config.properties"));
			value = (String) prop.get(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @author Mohan
	 * @param logger
	 * @return
	 *
	 */
	public static void Logger() {
		LogManager.getLogger(GenericLibrary.class.getName());
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param taking screenshot
	 * @throws Exception
	 *
	 */
	public static void getUrl(String URL) throws Exception {
		try {
			driver.get(URL);
		} catch (Exception e) {
			log.error("Unable to lounch the URL");
			throw new Exception("Failed:Unable to lounch the URL");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param taking screenshot
	 * @throws Exception
	 *
	 */
	public String ScreenCapture(String imgLocation) throws Exception {
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrfile, new File(imgLocation));
		} catch (Exception e) {
			log.error("Unable to take screenshot");
			throw new Exception("Failed:Unable to take screenshot");
		}
		return imgLocation;
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param js sendtext
	 *
	 */
	public static void jsTextEnter(WebElement element, String text) {
		js = (JavascriptExecutor) driver;
		js.executeScript("argumets[0].value=" + text + "", element);
	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param js Click
	 *
	 */
	public static void jsClick(String element) {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			log.info("Element clicked successfully");
		} catch (Exception e) {
			log.error("Element is not clicked successfully");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param switch to open the newly opened window
	 * 
	 */
	public static void switchToWindow() {
		homewindow = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(homewindow)) {
				driver.switchTo().window(window);
			}

		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param switch to main window
	 * 
	 */
	public static void switchToMainWindow() {
		for (String windowHandles : driver.getWindowHandles()) {
			if (!windowHandles.equals(homewindow)) {
				driver.switchTo().window(windowHandles);
				driver.close();
			}
			driver.switchTo().window(homewindow);
		}

	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param send the text to text box
	 * @throws Exception
	 * 
	 */
	public static void sendText(WebElement element, String text) throws Exception {
		try {
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(text);
			log.info("entered successfully");
		} catch (Exception e) {
			log.error("Unable to enter the text");
			throw new Exception("Failed:Unable to enter the text");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param click the element
	 * @throws Exception
	 * 
	 */
	public void click(WebElement element) throws Exception {
		try {
			element.click();
			log.info("Element clicked successfully");
		} catch (Exception e) {
			log.error("Unable to click the element");
			throw new Exception("Failed:Unable to click the elemen");
		}

	}

//***********************Waits*******************************//
	/**
	 * @author Mohan
	 * @param browser
	 * @param Implicitly wait
	 * @throws Exception
	 * 
	 */
	public static void dynamicWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param static wait or thread.sleep
	 * @throws InterruptedException 
	 * @throws Exception
	 * 
	 */
	
	public static void staticWait(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param wait for element visiblity
	 * @throws Exception
	 * 
	 */
	public static void waitForElementVisiblity(WebElement element) throws Exception {
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			log.error("Element is not visible");
			throw new Exception("Failed:Element is not visible");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Wait for until page load
	 * @return
	 * @throws Exception
	 * 
	 */
	public static void waitForUntilPageLoad() throws Exception {
		try {
			js = ((JavascriptExecutor) driver);
			js.executeScript("return document.readyState").equals("complete");
		} catch (Exception e) {
			log.error("Page is not get loaded");
			throw new Exception("Failed:Page is not get loaded");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Wait for element clickable
	 * @return
	 * @throws Exception
	 * 
	 */
	public static void waitForElementClickable(WebElement element) throws Exception {
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			log.error("Element is not clickable");
			throw new Exception("Failed:Element is not clickable");
		}
	}

	// *****************verification****************//
	/**
	 * @author Mohan
	 * @param browser
	 * @param Check element is clickable
	 * @return
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean elementIsPresent(WebElement element) throws Exception {
		boolean elementpresent = false;
		try {
			waitForElementVisiblity(element);
			if (element.isDisplayed()) {
				elementpresent = true;
			}
		} catch (Exception e) {
			log.error("Element is not present");
			throw new Exception("Failed:Element is not present");
		}
		return elementpresent;
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Check for element selectable
	 * @return
	 * @throws Exception
	 * 
	 */
	public static void elementIsSelected(WebElement element) throws Exception {
		try {
			element.isSelected();

		} catch (Exception e) {
			log.error("Element is not Selected");
			throw new Exception("Failed:Element is not Selected");
		}

	}

	// *******************Framehandle**************************//
	/**
	 * @author Mohan
	 * @param browser
	 * @param Switchtoframe
	 * @throws Exception
	 */
	public static void switchToFrame(WebElement element) throws Exception {
		try {
			driver.switchTo().frame(element);
		} catch (Exception e) {
			log.error("Unable to switch the frame");
			throw new Exception("Failed:Unable to switch the frame");
		}
	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param switchToParent frame
	 * @throws Exception
	 */
	public static void switchToDefautContent() throws Exception {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error("Unable to switch the frame");
			throw new Exception("Failed:Unable to switch the frame");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param navigate url
	 * @throws Exception
	 */
	public static void navigateToUrl(String url) throws Exception {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			log.error("Unable to navigate");
			throw new Exception("Failed:Unable to navigate");
		}
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param closebrowser
	 * @throws Exception
	 */
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param switch back the window
	 * @throws Exception
	 */
	public static void back() {
		driver.navigate().back();
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Refresh the page
	 * @throws Exception
	 */
	public static void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Create the current date and time
	 * @throws Exception
	 */
	public String currentDateAndTime() throws Exception {
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		String crntdate = dayOfMonth + "_" + monthValue + "_" + year + "_" + hour + "_" + minute + "_" + second;
		return crntdate;
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Create the current date
	 * 
	 */
	public String currentDate() {
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		String crntdate = dayOfMonth + "_" + monthValue + "_" + year;
		return crntdate;
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param Create the current time
	 */
	public Date currentTime() {
		LocalDateTime now = LocalDateTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		String crntdate = hour + "_" + minute + "_" + second;
		Date crntdate1 = null;
		return crntdate1;
	}

	/**
	 * @author Mohan
	 * @param browser
	 * @param	Select by visible text
	 * @throws Exception
	 */
	public static void selectByText(WebElement element, String text) throws Exception {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(text);
			log.info("visible text has selected");
		} catch (Exception e) {
			log.error("Unable to select visible text");
			throw new Exception("Failed:Unable to select visible text");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param Select by Index
	 * @throws Exception
	 */
	public static void selectByIndex(WebElement element, int index) throws Exception {
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			log.info("Index has selected");
		} catch (Exception e) {
			log.error("Unable to select Index");
			throw new Exception("Failed:Unable to select Index");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param Select by Value
	 * @throws Exception
	 */
	public static void selectByValue(WebElement element, String value) throws Exception {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			log.info("value has selected");
		} catch (Exception e) {
			log.error("Unable to select value");
			throw new Exception("Failed:Unable to select value");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param	deselect by value
	 * @throws Exception
	 */
	public static void deSelectByValue(WebElement element, String value) throws Exception {
		try {
			Select select = new Select(element);
			select.deselectByValue(value);
			log.info("value has Deselected");
		} catch (Exception e) {
			log.error("Unable to Deselect value");
			throw new Exception("Failed:Unable to Deselect value");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param	deselect by index
	 * @throws Exception
	 */
	public static void deSelectByIndex(WebElement element, int index) throws Exception {
		try {
			Select select = new Select(element);
			select.deselectByIndex(index);
			log.info("index value has Deselected");
		} catch (Exception e) {
			log.error("Unable to Deselect the index");
			throw new Exception("Failed:Unable to Deselect index");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param	deselect by text
	 * @throws Exception
	 */
	public static void deSelectByText(WebElement element, String text) throws Exception {
		try {
			Select select = new Select(element);
			select.deselectByVisibleText(text);
			log.info("visible text has Deselected");
		} catch (Exception e) {
			log.error("Unable to Deselect visible text");
			throw new Exception("Failed:Unable to Deselect visible text");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param	Get all values and select
	 * @throws Exception
	 */
	public static void getAllAndSelectValue(WebElement element, String text) throws Exception {
		try {
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			for (int i = 0; i <= options.size(); i++) {
				if (options.get(i).getText() == text)
					options.get(i).click();
			}
			log.info("visible text has selected");
		} catch (Exception e) {
			log.error("Unable to select visible text");
			throw new Exception("Failed:Unable to select visible text");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param scroll the page to target element
	 */
	public static void scrollToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param duble click on target element
	 */
	public static void dubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param Right click on target element
	 */
	public static void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param accept the alert
	 */
	public static  void acceptAlert() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			log.info("Alert is accepted");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error("Unable to accept the alert");
			throw new Exception("Failed:Unable to accept the alert");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param dismiss the alert
	 */
	public static void dismissAlert() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			log.info("Alert is dismissed");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error("Unable to dismiss the alert");
			throw new Exception("Failed:Unable to dismiss the alert");
		}

	}
	/**
	 * @author Mohan
	 * @param browser
	 * @param enter text and confirm the alert
	 */
	public static void sendTextAndAccept(String keysToSend) throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(keysToSend);
			log.info("Text entered successfully");
			alert.accept();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error("Unable to accept the alert");
			throw new Exception("Failed:Unable to accept the alert");
		}

	}
}
