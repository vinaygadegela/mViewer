package com.imaginea.testListeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter{
	WebDriver driver;
	
	public void onTestFailure(ITestResult tr){
		  driver=(WebDriver)tr.getTestContext().getAttribute("driverObj");
		  takeScreenShot(tr.getName());
	}
	
	public void onTestSuccess(ITestResult tr) {
	}

	public void onTestSkipped(ITestResult tr) {
	}
	
	public void takeScreenShot(String testName){
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile,new File((System.getProperty("user.dir")+"//ScreenShots//"+testName+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
