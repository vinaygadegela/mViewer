package com.imaginea.base;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	private final Logger log = LogManager.getLogger(BaseTest.class.getName());
	private WebDriver driver;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser) {
		try {
			driver = DriverFactory.getDriver(browser);
			driver.manage().window().maximize();
			DriverManager.setDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getDriver(){
		log.info("Ïnitializing class: "+this);
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver, this);
		return driver;
	}
}
