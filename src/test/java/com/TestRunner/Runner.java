package com.TestRunner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


//import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = "com.air_asia_stepdefintion", plugin = {
		"html:Reports/cucumberreports.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, monochrome = true,dryRun = true)
public class Runner {
	


}