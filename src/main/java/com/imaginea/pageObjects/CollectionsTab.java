package com.imaginea.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;

import com.imaginea.utils.ExcelManager;
import com.imaginea.utils.UIUtility;

public class CollectionsTab extends HomePage {

	private Logger log = Logger.getLogger(CollectionsTab.class);

	private String collectionSettingsGear = "a[label='%s']+a";
	private String dropCollection = "div[id='%s_subMenu'] ul>li:nth-child(2)>a";

	String clickCollection = "//*[@class='yui3-menu-toggle'][@href='#%s_subMenu']";
	String addDoc = "//a[@text(),Add Document]";
	// String dropCollection = "//a[@text(),Drop Collection]";
	String statstics = "//a[@text(),Statistics]";
	String yesButton = "//button[text()='Yes']";
	String noButton = "//button[text()='No']";
	String textBox = ".bd>form>textarea";
	String submitDocument = "//button[text()='Submit']";

	@FindBy(css = "#collNames span")
	private List<WebElement> collNames;

	@FindBy(id = "collNames")
	private WebElement collections;

	public CollectionsTab(WebDriver driver) {
		super(driver);
	}

	public void addDocument(String collectionName, String docName) {
		String collection = String.format(clickCollection, collectionName);
		clickElement(collection);
		clickElement(addDoc);
		clearText(textBox);
		ExcelManager excel = new ExcelManager(System.getProperty("user.dir") + "src\\main\\resources");
		String docData = excel.getCellDataXLS("ADD", 4, 1);
		clickElement(yesButton);
	}

	public void dropCollection(String collectionName) {
		UIUtility.sleep(30000);
		String locator = String.format(collectionSettingsGear, collectionName);
		Actions actions = new Actions(driver);
		actions.moveToElement(findElementByCssSelector(driver, locator)).build().perform();
		findElementByCssSelector(driver, locator).click();
		waitForElementVisibility(driver, 30,
				findElementByCssSelector(driver, String.format(dropCollection, collectionName)));
		actions.moveToElement(findElementByCssSelector(driver, String.format(dropCollection)).build().perform();
		findElementByCssSelector(driver, dropCollection).click();
		clickElement(yesButton);

	}

	
	public void Stastics(String collectionName) {
		String collection = String.format(clickCollection, collectionName);
		clickElement(collection);
		clickElement(statstics);
	}

	public List<String> getCollectionsList(WebDriver driver) {
		waitForElementVisibility(driver, 60, collections);
		return convertWebElementListToString(collNames);
	}
}
