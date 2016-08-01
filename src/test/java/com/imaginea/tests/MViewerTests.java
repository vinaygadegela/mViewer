package com.imaginea.tests;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imaginea.base.BaseTest;
import com.imaginea.pageObjects.DataBasesTab;
import com.imaginea.pageObjects.HomePage;
import com.imaginea.pageObjects.LoginPage;
import com.imaginea.utils.UIUtility;

public class MViewerTests extends BaseTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DataBasesTab dataBaseTab;
	private String url = "http://172.16.55.67:8081/index.html";

	@BeforeClass
	public void beforeClass() {
		driver = getDriver();
		driver.get(url);
	}

	@Test
	public void CreateDataBase() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
	}

	@Test
	public void addCollection() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UUID.randomUUID().toString().substring(0, 12);
		dataBaseTab.createDb(dbName);
		System.out.println(dataBaseTab.getDBList(driver));
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		String collectName = UUID.randomUUID().toString().substring(0, 12);
		dataBaseTab.addCollection(dbName, collectName);

	}

	@Test
	public void dropDatabase() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		dataBaseTab.dropDb(dbName);
		Assert.assertFalse(dataBaseTab.getDBList(driver).contains(dbName), "Database is not yet deleted");

	}

}
