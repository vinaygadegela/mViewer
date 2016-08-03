package com.imaginea.tests;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imaginea.base.BaseTest;
import com.imaginea.pageObjects.CollectionsTab;
import com.imaginea.pageObjects.DataBasesTab;
import com.imaginea.pageObjects.HomePage;
import com.imaginea.pageObjects.LoginPage;
import com.imaginea.utils.UIUtility;

public class MViewerTests extends BaseTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DataBasesTab dataBaseTab;
	private CollectionsTab collectionsTab;
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
		dataBaseTab.dropDb(dbName);
	}

	@Test
	public void CreateDuplicateDataBase() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		dataBaseTab.createDb(dbName);
		Assert.assertEquals(dataBaseTab.getInfoMessage(),
				"DB creation failed ! DB with name '" + dbName + "' ALREADY EXISTS.");
		dataBaseTab.dropDb(dbName);

	}

	@Test
	public void CreateDataBaseWithNameSpace() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString().substring(0, 6) + " "
				+ UIUtility.generateRandomString().substring(0, 6);
		dataBaseTab.createDb(dbName);
		Assert.assertEquals(dataBaseTab.getInfoMessage(),
				"DB creation failed ! Invalid ns [" + dbName + ".system.namespaces].");
	}

	@Test
	public void addCollection() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		String collectName = UUID.randomUUID().toString().substring(0, 12);
		dataBaseTab.addCollection(dbName, collectName);
		Assert.assertEquals(dataBaseTab.getInfoMessage(),
				"Collection [" + collectName + "] added to Database [" + dbName + "].");

	}

	@Test
	public void addGridFSBucket() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		String bucketName = UIUtility.generateRandomString();
		dataBaseTab.addBucket(dbName, bucketName);
		Assert.assertEquals(dataBaseTab.getInfoMessage(),
				"GridFS bucket [" + bucketName + "] added to database [" + dbName + "].");
	}

	@Test
	public void dropCollection() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		String collectName = UUID.randomUUID().toString().substring(0, 12);
		dataBaseTab.addCollection(dbName, collectName);
		collectionsTab = new CollectionsTab(driver);
		collectionsTab.dropCollection(collectName);
		Assert.assertEquals(dataBaseTab.getInfoMessage(),
				"Collection [" + collectName + "] has been deleted from Database [" + dbName + "].");
	}

	@Test
	public void dropDatabase() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = UIUtility.generateRandomString();
		System.out.println(dbName);
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		int initial = dataBaseTab.getDBList(driver).size();
		dataBaseTab.dropDb(dbName);
		int after = dataBaseTab.getDBList(driver).size();

		System.out.println(dataBaseTab.getDBList(driver));
		Assert.assertEquals(initial - after, 0);

	}

}
