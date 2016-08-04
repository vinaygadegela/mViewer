package com.imaginea.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;

import com.imaginea.utils.UIUtility;

public class DataBasesTab extends HomePage {

	private Logger log = Logger.getLogger(DataBasesTab.class);

	@FindBy(id = "infoText")
	private WebElement infoMessage;

	@FindBy(id = "createDB")
	private WebElement createDb;

	@FindBy(css = "#addDBDialog form[method='post'] input")
	private WebElement dbNameTextBox;

	@FindBy(css = "#addDBDialog #yui-gen0-button")
	private WebElement submitButton;

	@FindBy(css = "#dbNames span")
	private List<WebElement> dbNames;

	@FindBy(xpath = "//a[text()='Add Collection']")
	private WebElement addCollectionLink;

	private String dbSettingsGear = "a[href='#%s_subMenu']";
	private String addCollection = "div[id='%s_subMenu'] ul>li:nth-child(1)>a";
	private String addGridFSBucket = "div[id='%s_subMenu'] ul>li:nth-child(2)>a";
	private String dropDatabase = "div[id='%s_subMenu'] ul>li:nth-child(3)>a";
	private String statistics = "div[id='%s_subMenu'] ul>li:nth-child(4)>a";
	private String jQuerySelector = "'#yui-gen0-button'";
	private String confirmYesButton = ".button-group>span:nth-of-type(1) button";

	@FindBy(css = "#addColDialog input[type='text']")
	private WebElement collectionName;
	@FindBy(css = "#addColDialog [for='collSize']+input")
	private WebElement size;
	@FindBy(css = "#addColDialog [for='collMaxSize']+input")
	private WebElement maxDocuments;
	@FindBy(css = "#addColDialog #yui-gen2-button")
	private WebElement submit;
	@FindBy(css = "#addColDialog #yui-gen3-button")
	private WebElement cancel;
	@FindBy(css = "#yui-gen0-button")
	private WebElement yes;
	@FindBy(css = "[method='GET'] input")
	private WebElement bucketNames;
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement yes_ConfirmDropDB;

	/**
	 * Constructor to initialize Database tab
	 * 
	 * @param driver
	 */
	public DataBasesTab(WebDriver driver) {
		super(driver);
	}

	/**
	 * Create Database
	 * 
	 * @param dbName
	 */
	public void createDb(String dbName) {
		waitForElementVisibility(driver, 20, createDb);
		waitForElementClickable(driver, 30, createDb);
		createDb.click();
		waitForElementVisibility(driver, 20, dbNameTextBox);
		dbNameTextBox.clear();
		dbNameTextBox.sendKeys(dbName);
		submitButton.click();
		UIUtility.sleep(5000);
	}

	public void dropDb(String dbName) {
		String locator = String.format(dbSettingsGear, dbName);
		Actions actions = new Actions(driver);
		actions.moveToElement(findElementByCssSelector(driver, locator))
				.build().perform();
		findElementByCssSelector(driver, locator).click();
		waitForElementVisibility(
				driver,
				30,
				findElementByCssSelector(driver,
						(String.format(dropDatabase, dbName))));
		actions.moveToElement(
				driver.findElement(By.cssSelector(String.format(dropDatabase,
						dbName)))).build().perform();

		findElementByCssSelector(driver, String.format(dropDatabase, dbName))
				.click();
		clickElementusingJquery(driver, confirmYesButton);
		switchToAlert_Accept(driver);
		UIUtility.sleep(30000);
	}

	public void addCollection(String dbName, String collectName) {
		clickAddCollection(dbName, collectName);

	}

	public void clickAddCollection(String dbName, String collectName) {
		String locator = String.format(dbSettingsGear, dbName);
		waitForPageLoad(driver);
		Actions actions = new Actions(driver);
		WebElement dbElement = driver.findElement(By.cssSelector(locator));
		waitForElementClickable(driver, 40, dbElement);
		dbElement.click();
		String locator1 = "//*[@id='%s_subMenu']";
		String locator2 = String.format(addCollection, dbName);
		driver.manage().timeouts().implicitlyWait(10000L, TimeUnit.MILLISECONDS);		
		driver.manage().timeouts().implicitlyWait(10000L, TimeUnit.MILLISECONDS);
		actions.moveToElement(
				driver.findElement(By.xpath(String.format(locator1, dbName))))
				.click().build().perform();
		waitForElementClickable(driver, 30, driver.findElement(By
				.cssSelector(String.format(locator2, dbName))));
		driver.manage().timeouts()
				.implicitlyWait(10000L, TimeUnit.MILLISECONDS);
		actions.moveToElement(
				driver.findElement(By.cssSelector(String.format(locator2,
						dbName)))).click().build().perform();
		waitForElementVisibility(driver, 30, collectionName);
		collectionName.sendKeys(collectName);
		size.clear();
		size.sendKeys("1024");
		maxDocuments.clear();
		maxDocuments.sendKeys("100");
		waitForElementVisibility(driver, 30, submit);
		submit.click();

	}

	/**
	 * Get List of DB
	 * 
	 * @return
	 */
	public List<String> getDBList(WebDriver driver) {
		waitForElementVisibility(driver, 60, createDb);
		return convertWebElementListToString(dbNames);
	}

	/**
	 * Get Info Message when error message is displayed
	 * 
	 * @return
	 */
	public String getInfoMessage() {
		waitForElementVisibility(driver, 20, infoMessage);
		return infoMessage.getText();
	}

	public void addBucket(String dbName, String bucketName) {
		String locator = String.format(dbSettingsGear, dbName);
		waitForElementClickable(driver, 30,
				findElementByCssSelector(driver, locator));
		Actions actions = new Actions(driver);
		actions.moveToElement(findElementByCssSelector(driver, locator))
				.build().perform();

		driver.findElement(By.cssSelector(locator)).click();
		String locator1 = "//*[@id='%s_subMenu']";
		waitForElementVisibility(driver, 30,
				driver.findElement(By.xpath(String.format(locator1, dbName))));
		actions.moveToElement(
				driver.findElement(By.xpath(String.format(locator1, dbName))))
				.build().perform();
		String locator2 = String.format(addGridFSBucket, dbName);
		waitForElementClickable(driver, 30,
				findElementByCssSelector(driver, locator2));
		while (findElementByCssSelector(driver, locator2).isDisplayed()) {
			clickElementUsingJS(driver,
					findElementByCssSelector(driver, locator2));
		}
		waitForElementVisibility(driver, 30, bucketNames);
		bucketNames.clear();
		bucketNames.sendKeys(bucketName);
		clickElementUsingJS(driver, yes);

	}
}
