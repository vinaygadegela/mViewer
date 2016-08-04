package com.imaginea.tests;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
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

/**
 * All Test Case Of Collection Tab should be updated in this
 * 
 * @author Nellore Krishna Kumar
 *
 */
public class CollectionTestCase extends BaseTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DataBasesTab dataBaseTab;
	private CollectionsTab collectionsTab;
	private String url;

	@BeforeClass
	public void beforeClass() {
		url = ConfigUtil.getInstance().getProperty("url");
		driver = getDriver();
		driver.get(url);
	}

	@Test(description = "Test Case to Check Document Addition to Collection")
	public void testAddDocumentToCollection() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickGoButton();
		dataBaseTab = new DataBasesTab(driver);
		String dbName = "DB" + UUID.randomUUID().toString().substring(0, 3);
		dataBaseTab.createDb(dbName);
		Assert.assertTrue(dataBaseTab.getDBList(driver).contains(dbName),
				"Created Database is not available");
		String collectName = "COLL";
		dataBaseTab.addCollection(dbName, collectName);
		collectionsTab = new CollectionsTab(driver);
		collectionsTab.addDocument(collectName, collectName.concat("Doc"));
		String expectedMessage = String.format(
				"New document added successfully to collection '%s'",
				collectName);
		Assert.assertEquals(collectionsTab.getInfoText(), expectedMessage,
				"Expected message text didn't match");
	}

}
