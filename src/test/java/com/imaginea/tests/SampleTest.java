package com.imaginea.tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.imaginea.base.BaseTest;
import com.imaginea.pageObjects.HomePage;
import com.imaginea.pageObjects.LoginPage;

public class SampleTest extends BaseTest {

	private WebDriver driver;
	private Logger log =Logger.getLogger(SampleTest.class); 
	private LoginPage loginPage;
	private HomePage homePage;
	@BeforeClass
	public void beforeClass(){
		driver=getDriver();
	}
	
	@Test
	public void launch(){
		log.debug("Running test case ....");
		driver.get("http://172.16.55.67:8081/index.html");
		loginPage= new LoginPage(driver);
		homePage=loginPage.clickGoButton();
		homePage.createNewDataBase();
	}
}
