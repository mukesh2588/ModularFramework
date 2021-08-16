package com.guru99.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest{
	@Parameters({"username","userPassword"})
	@Test
	public void verifyUserLoginWithCorrectCredentials(String username, String password)
	{
		reportUtils.createATestCase("verifyUserLoginWithCorrectCredentials");
		reportUtils.addTestLog(Status.INFO, "performing Login");
		loginpage.loginToApplication(username, password);
		String expectedTitle="Guru99 Bank Manager Home Page";
		String actualTitle=comDriver.getTitleOfThePage();
		reportUtils.addTestLog(Status.INFO, "Compairing expected and actual title");
		Assert.assertEquals(actualTitle,expectedTitle);
	}

}
