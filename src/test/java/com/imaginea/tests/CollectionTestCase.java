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
import com.imaginea.utils.ConfigUtil;
import com.imaginea.utils.UIUtility;

public class CollectionTestCase extends BaseTest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DataBasesTab dataBaseTab;
	private CollectionsTab collectionsTab;
	private String url ;

	@BeforeClass
	public void beforeClass() {
		url = ConfigUtil.getInstance().getProperty("url");
		driver = getDriver();
		driver.get(url);
	}

	@Test
	public void testAddDocumentToCollection(){
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = "Krishna";
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName), "Created Database is not available");
		String collectName = "collection";
		dataBaseTab.addCollection(dbName, collectName);		
		collectionsTab = new CollectionsTab(driver);
		collectionsTab.addDocument(collectName, "krishna");

	}
	


}
