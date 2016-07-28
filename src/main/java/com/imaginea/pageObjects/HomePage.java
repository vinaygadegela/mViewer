package com.imaginea.pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.imaginea.utils.UIUtility;

public class HomePage extends UIUtility{
	private Logger log =LogManager.getLogger(HomePage.class.getName()); 
	@FindBy(id="createDB") private WebElement newButton;
	@FindBy(css="#addDBDialog>div:nth-of-type(2) input") private WebElement inputDBTextBox;
	@FindBy(id="yui-gen0-button") private WebElement submitButton;
	
	@FindBy(id="home") private WebElement homeMenu;;
	
	
	public HomePage(WebDriver driver){
		super(driver);
		initPage(homeMenu);
	}
	
	public void createNewDataBase(){
		log.info("Creating new DataBase..");
		click(newButton);
		Type(inputDBTextBox, "MyBD");
		click(submitButton);
	}
}
