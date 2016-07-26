package com.imaginea.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;


public class LoginPage extends BasePage{
	private WebDriver driver;

	Logger log =Logger.getLogger(LoginPage.class); 
	@FindBy(id="login")private WebElement goButton; 
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
	}
	public void clickGoButton(){
		goButton.click();
		log.info("Clicking on Go Button");
	}

}
