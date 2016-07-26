package com.imaginea.tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.imaginea.base.BaseTest;
import com.imaginea.pageObjects.LoginPage;

public class SampleTest extends BaseTest {

	WebDriver driver;
	Logger log =Logger.getLogger(SampleTest.class); 
	LoginPage loginPage;
	@BeforeClass
	public void beforeClass(){
		driver=getDriver();
	}
	@Test
	public void launch(){
		log.debug("Running test case ....");
		/*driver.get("http://mvnrepository.com/artifact/org.testng/testng/6.9.10");*/
		driver.get("http://172.16.55.67:8081/index.html");
		loginPage= new LoginPage(driver);
		loginPage.clickGoButton();
	}
}
