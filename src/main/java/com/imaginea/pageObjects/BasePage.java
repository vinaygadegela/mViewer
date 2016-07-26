package com.imaginea.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.imaginea.utils.UIUtility;

public abstract class BasePage {
	protected WebDriver driver;
	protected static final int pageTimeoutTime = 15;
	protected final Logger logger = Logger.getLogger(getClass());
	protected long pageLoadStartTime;
	protected long pageLoadEndTime;
	

	protected void markPageLoadStartTime() {
		pageLoadStartTime = System.currentTimeMillis();
	}
	protected long markPageLoadEndTime() {
		pageLoadEndTime = System.currentTimeMillis();
		return pageLoadEndTime - pageLoadStartTime;
	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public BasePage(){
		
	}
	/**
	 * Initialize the page
	 * @param initialElement The webElement(s) to validate
	 */
	public void initPage(WebElement initialElement, WebElement... initialElements) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, pageTimeoutTime), this);
		if (initialElement != null) {
			UIUtility.waitForElementVisibility(driver, 15, initialElement);
			for (WebElement element : initialElements) {
				UIUtility.waitForElementVisibility(driver, 15, element);
			}
		}
	}

}