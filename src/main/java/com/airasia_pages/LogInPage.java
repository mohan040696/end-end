package com.airasia_pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.resources.GenericLibrary;

public class LogInPage {
public LogInPage() {
	PageFactory.initElements(GenericLibrary.driver, this);
}

//Login button
@FindBy(xpath = "//p[text()='Login']")
WebElement loginButton;


//mobile number text box
@FindBy(id="mobile-input-sso")
WebElement mobileNumbnerLogin;


//login with password btn
@FindBy(xpath = "//span[@class='pwd']")
WebElement logInWithPwdBtn;

//Password text box
@FindBy(id="password-input-sso-login")
WebElement passwordTextBox;

//continue btn in login page
@FindBy(xpath = "//button[text()='Continue']")
WebElement continueButton;

public WebElement getLoginButton() {
	return loginButton;
}

public WebElement getMobileNumbnerLogin() {
	return mobileNumbnerLogin;
}

public WebElement getLogInWithPwdBtn() {
	return logInWithPwdBtn;
}

public WebElement getPasswordTextBox() {
	return passwordTextBox;
}

public WebElement getContinueButton() {
	return continueButton;
}
}
