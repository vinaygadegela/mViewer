package com.imaginea.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return threadLocal.get();
	}

	public static void setDriver(WebDriver driver) {
		threadLocal.set(driver);
	}

	public static void closeDriver() {
		if (getDriver() != null) {
			try {
				getDriver().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				getDriver().quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		threadLocal.remove();
	}

}
