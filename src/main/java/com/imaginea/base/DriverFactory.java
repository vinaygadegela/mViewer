package com.imaginea.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import com.imaginea.utils.TestUtils;

public class DriverFactory {
	
	public static WebDriver getDriver(String browserName) throws Exception {
		switch (browserName) {
		case BrowserType.CHROME:
			return getChromeDriver();
		case BrowserType.FIREFOX:
			return new FirefoxDriver();
		default:
			throw new Exception("Invalid browser");
		}
	}

	private static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",TestUtils.getDriverPath("chrome"));
		return new ChromeDriver();
	}

}
