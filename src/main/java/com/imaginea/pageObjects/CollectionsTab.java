package com.imaginea.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.imaginea.utils.ExcelManager;

public class CollectionsTab extends HomePage {

	private Logger log = Logger.getLogger(CollectionsTab.class);

	String clickCollection = "//*[@class='yui3-menu-toggle'][@href='#%s_subMenu']";
	String addDoc = "//a[@text(),Add Document]";
	String dropCollection = "//a[@text(),Drop Collection]";
	String statstics = "//a[@text(),Statistics]";
	String yesButton="//button[text()='Yes']";
	String noButton="//button[text()='No']";
	String textBox=".bd>form>textarea";
	String submitDocument="//button[text()='Submit']";

	public CollectionsTab(WebDriver driver) {
		super(driver);
	}

	public void addDocument(String collectionName, String docName) {
		String collection = String.format(clickCollection, collectionName);
		clickElement(collection);
		clickElement(addDoc);
		clearText(textBox);
		ExcelManager excel=new ExcelManager( System.getProperty("user.dir")+"src\\main\\resources");
		String docData=excel.getCellDataXLS("ADD", 4, 1);
		clickElement(yesButton);
	}



	public void dropCollection(String collectionName) {
		String collection = String.format(clickCollection, collectionName);
		clickElement(collection);
		clickElement(dropCollection);
		clickElement(yesButton);
	}

	public void Stastics(String collectionName) {
		String collection = String.format(clickCollection, collectionName);
		clickElement(collection);
		clickElement(statstics);
	}
}
