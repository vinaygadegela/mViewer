package com.imaginea.base;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.imaginea.testListeners.EventFireWebDriver;
import com.imaginea.testListeners.TestListener;
import com.imaginea.utils.FileUtilities;

@Listeners(TestListener.class)
public class BaseTest {
	
	private final Logger log = LogManager.getLogger(BaseTest.class.getName());
	private WebDriver driver;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser,ITestContext context) {
		try {
			driver = DriverFactory.getDriver(browser);
			driver.manage().window().maximize();
			DriverManager.setDriver(driver);
			driver= registerEvents(driver);
			context.setAttribute("driverObj",driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver(){
		log.info("getting the Driver "+this);
		if(driver==null){
		driver=DriverManager.getDriver();
		}
		PageFactory.initElements(driver, this);
		return driver;
	}
	
	public WebDriver registerEvents(WebDriver driver) {
		EventFiringWebDriver ef = new EventFiringWebDriver(driver);
		driver = ef.register(new EventFireWebDriver());
		return driver;
	}
	@AfterClass
	public void closeDriver(){
		driver.close();
	}
	
	//@BeforeSuite
	public void setupTestSuite(){
		FileUtilities utils= new FileUtilities();
		utils.deleteExisitngFolder("src\\Logs");
	}
}
