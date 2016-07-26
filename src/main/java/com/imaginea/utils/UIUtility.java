package com.imaginea.utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class UIUtility {
	private Logger log = Logger.getLogger(UIUtility.class);
	public  final  int STALENESS_MAX_RETRY_COUNT=2;
	int stale_count=1;
	WebDriver driver;

	public UIUtility(WebDriver driver){
		this.driver=driver;
	}
	public void Type(By locator,String text_to_type){
		log.info("Trying to enter the text following text ..... "+text_to_type);
		WebElement element=getWebElement( locator);
		element.sendKeys(text_to_type);
		log.info("Typed the text successfully... ");
	}
	
	public void click(WebElement element){
		element.click();
	}
	public WebElement getWebElement( final By locator){
		WebElement element=null;
		try{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			element= wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});
			waitForPageLoad(driver);
		}
		catch(TimeoutException we){
			log.error("Failed to retrieve the element within the time out!! locator details are"+getLocatorDetails(locator));
			log.error("stack trace is"+we);
			assert false:"Time out exception is occurred";

		}
		catch(InvalidSelectorException ie){
			log.error("invalid xpath/css");
			assert false:"InvalidSelector Exception is occurred";

		}
		catch(NoSuchElementException we){
			log.error("Failed to retrieve the element, check the xpath/css selector!! locator details are"+getLocatorDetails(locator));
			log.error("stack trace is"+we);
			assert false:"WebDriver exception is occurred";

		}
		catch(StaleElementReferenceException se){
			if(stale_count<=STALENESS_MAX_RETRY_COUNT){
				log.info("Staleness is observed and rrtying ");
				getWebElement(locator);
				stale_count++;
			}else{
				log.error("Stale element exception is occurred!!!!");
				log.error("Stack trace is"+se);
			}
		}

		catch(WebDriverException we){
			log.error("WebDriver exception is occurred"+we);
			log.error("stack trace is"+we);
			assert false:"NoSuchElement Exception is occurred";

		}

		catch(Exception e){
			log.error("Failed to retrieve the element!! locator details are"+getLocatorDetails(locator));
			log.error("Exception stack trace is:"+e);
			assert false:"Other Exception is occurred";

		}
		finally{
			stale_count=1;
		}
		return element;

	}

	public  void waitForPageLoad(WebDriver driver){
		while(true){
			String page_status =(String)((JavascriptExecutor)driver).executeScript("return document.readyState");
			log.info("Loading........".toUpperCase());
			if(page_status.equals("complete")){
				break;
			}
		}
	}

	private  final String getLocatorDetails(By locator){
		String[] tokens=locator.toString().split(":");
		return tokens[tokens.length-1].trim();
	}
	
	public static void waitForElementVisibility(WebDriver driver, int timeout, WebElement element) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
}
}
