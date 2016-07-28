package com.imaginea.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imaginea.base.BaseTest;
import com.imaginea.pageObjects.DataBasesTab;
import com.imaginea.pageObjects.HomePage;
import com.imaginea.pageObjects.LoginPage;

public class MViewerTests extends BaseTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DataBasesTab dataBaseTab;
	private String url="http://172.16.55.67:8081/index.html";
	
	@BeforeClass
	public void beforeClass(){
		driver=getDriver();
		driver.get(url);
	}
	
	@Test
	public void CreateDBAndCollection(){
		loginPage= new LoginPage(driver);
		homePage=loginPage.clickGoButton();
		dataBaseTab= new DataBasesTab(driver);
		
	}
}
