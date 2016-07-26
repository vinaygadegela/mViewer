package com.imaginea.pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.imaginea.utils.UIUtility;


public class LoginPage extends UIUtility{
	private WebDriver driver;
	private Logger log =LogManager.getLogger(LoginPage.class.getName()); 
	@FindBy(id="login") private WebElement goButton; 
	
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		initPage(goButton);
		
	}
	public HomePage clickGoButton(){
		log.info("Clicking on Go Button");
		goButton.click();
		return new HomePage(driver);
	}

}
