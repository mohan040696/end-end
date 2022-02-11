package com.air_asia_stepdefintion;

import java.util.HashMap;
import java.util.List;
import org.testng.Assert;

import com.pom_manager.PomManager;
import com.resources.DataDriven;
import com.resources.GenericLibrary;
import io.cucumber.java.en.*;

public class AirAsiaLogin extends GenericLibrary {
	GenericLibrary generic = new GenericLibrary();
	static int testRow = 1;
	String path = System.getProperty("user.dir") + loadProperties("AirAsiaLogin");
	String sheetname = loadProperties("AirAsiaLoginSheetName");
	List<HashMap<String, String>> testData = DataDriven.readDataExcel(path, sheetname);
	PomManager pommanager = new PomManager();
	@Given("User launch the AirAsia Application")
	public void user_launch_the_AirAsia_Application() throws Throwable {
		getUrl(loadProperties("AIR_ASIA_URL"));
	}

	@When("^User login into air asia application with \"([^\"]*)\",\"([^\"]*)\"$")
	public void user_login_into_air_asia_application_with(String MOBILENUMBER, String PASSWORD) throws Throwable {
		waitForElementClickable(pommanager.getLoginpage().getLoginButton());
		click(pommanager.getLoginpage().getLoginButton());
		waitForElementClickable(pommanager.getLoginpage().getMobileNumbnerLogin());
		Assert.assertTrue(elementIsPresent(pommanager.getLoginpage().getMobileNumbnerLogin()));
		sendText(pommanager.getLoginpage().getMobileNumbnerLogin(), testData.get(testRow).get(MOBILENUMBER));
		click(pommanager.getLoginpage().getContinueButton());
		waitForElementClickable(pommanager.getLoginpage().getLogInWithPwdBtn());
		Assert.assertTrue(elementIsPresent(pommanager.getLoginpage().getLogInWithPwdBtn()));
		click(pommanager.getLoginpage().getLogInWithPwdBtn());
		waitForElementVisiblity(pommanager.getLoginpage().getPasswordTextBox());
		sendText(pommanager.getLoginpage().getPasswordTextBox(), testData.get(testRow).get(PASSWORD));
		staticWait(2000);
		click(pommanager.getLoginpage().getContinueButton());
	}

}
