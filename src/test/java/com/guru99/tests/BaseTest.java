package com.guru99.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;

public class BaseTest {
	
	CommonDriver comDriver;
	String url;
	WebDriver driver;
	LoginPage loginpage;
	String currentWrokingDirectoy;
	String configFileName;
	Properties configProperty;
	ReportUtils reportUtils;
	String reportFileName;
	ScreenshotUtils screenshot;
	long executionTime;
	
	@BeforeSuite
	public void preSetup() throws Exception {
		currentWrokingDirectoy=System.getProperty("user.dir");
		configFileName=currentWrokingDirectoy+"/config/config.properties";
		//reportFileName=currentWrokingDirectoy+"/reports/guru99TestReport.html";
		reportFileName=currentWrokingDirectoy+"/reports/guru99TestReport.html";
		configProperty=ConfigUtils.readProperties(configFileName);
		reportUtils=new ReportUtils(reportFileName);
	}
	
	@BeforeClass
	public void setup() throws Exception
	{
		url=configProperty.getProperty("baseUrl");
		String browserType=configProperty.getProperty("browserType");
		comDriver=new CommonDriver(browserType);
		driver=comDriver.getDriver();
		loginpage=new LoginPage(driver);
		screenshot=new ScreenshotUtils(driver);
		comDriver.navigateToUrl(url);
	}
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception {
		String testcasename=result.getName();
		//long executionTime=System.currentTimeMillis();
		 executionTime=System.currentTimeMillis();
		String scrrenshotFileName=currentWrokingDirectoy+"/screenshots/"+testcasename+executionTime+".jpeg";
		if(result.getStatus()==ITestResult.FAILURE) {
			reportUtils.addTestLog(Status.FAIL, "One or more step failed");
			screenshot.captureAndSaveScreenshot(scrrenshotFileName);
			reportUtils.attachScreenshotToReport(scrrenshotFileName);
		}
	}
	@AfterClass
	public void tearDown()
	{
		comDriver.closeAllBrowser();
	}
	
	@AfterSuite
	public void postTearDown() {
		reportUtils.flushReport();
	}



}
