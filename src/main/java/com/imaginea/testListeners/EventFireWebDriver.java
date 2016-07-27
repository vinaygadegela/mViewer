package com.imaginea.testListeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class EventFireWebDriver extends AbstractWebDriverEventListener {
	
		private Logger log= LogManager.getLogger(getClass()) ;
	
		public void beforeNavigateTo(String url, WebDriver driver) {
		    // Do nothing.
			
		  }

		  public void afterNavigateTo(String url, WebDriver driver) {
		    // Do nothing.
			  log.info("user  is navigated to:'"+url); 
		  }

		  public void beforeNavigateBack(WebDriver driver) {
		    // Do nothing.
		  }

		  public void afterNavigateBack(WebDriver driver) {
		    // Do nothing.
		  }

		  public void beforeNavigateForward(WebDriver driver) {
		    // Do nothing.
		  }

		  public void afterNavigateForward(WebDriver driver) {
		    // Do nothing.
		  }

		  public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		    // Do nothing.
		  }

		  public void afterFindBy(By by, WebElement element, WebDriver driver) {
		    // Do nothing.
		  }

		  public void beforeClickOn(WebElement element, WebDriver driver) {
			  log.info("Going to click on "+element);
		  }

		  public void afterClickOn(WebElement element, WebDriver driver) {
			  log.info("Successfuly clicked on "+element);
		  }

		  public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		    // Do nothing.
		  }

		  public void afterChangeValueOf(WebElement element, WebDriver driver) {
		    // Do nothing.
		  }

		  public void beforeScript(String script, WebDriver driver) {
		    // Do nothing
		  }

		  public void afterScript(String script, WebDriver driver) {
		    // Do nothing
		  }

		  public void onException(Throwable throwable, WebDriver driver) {
		    // Do nothing
		  }
}
