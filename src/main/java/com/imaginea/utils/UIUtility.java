package com.imaginea.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class UIUtility {

	protected static final int pageTimeoutTime = 15;
	protected final Logger log = Logger.getLogger(getClass());
	protected long pageLoadStartTime;
	protected long pageLoadEndTime;
	public final int STALENESS_MAX_RETRY_COUNT = 2;
	int stale_count = 1;
	protected WebDriver driver;

	public UIUtility(WebDriver driver) {
		this.driver = driver;
	}

	protected void markPageLoadStartTime() {
		pageLoadStartTime = System.currentTimeMillis();
	}

	protected long markPageLoadEndTime() {
		pageLoadEndTime = System.currentTimeMillis();
		return pageLoadEndTime - pageLoadStartTime;
	}

	/**
	 * Initialize the page
	 * 
	 * @param initialElement
	 *            The webElement(s) to validate
	 */
	public void initPage(WebElement initialElement, WebElement... initialElements) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, pageTimeoutTime), this);
		if (initialElement != null) {
			waitForElementVisibility(driver, 15, initialElement);
			for (WebElement element : initialElements) {
				waitForElementVisibility(driver, 15, element);
			}
		} /*
			 * else{ throw new
			 * NullPointerException("Initial Element is null or page is not loaded"
			 * ); }
			 */
	}

	public void Type(WebElement element, String text_to_type) {
		log.info("Trying to enter the text following text ..... " + text_to_type);
		element.sendKeys(text_to_type);
		log.info("Typed the text successfully... ");
	}

	public void click(WebElement element) {
		log.info("Trying to click on element ..... " + element);
		element.click();
	}

	public WebElement getWebElement(final By locator) {
		WebElement element = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});
			waitForPageLoad(driver);
		} catch (TimeoutException we) {
			log.error("Failed to retrieve the element within the time out!! locator details are"
					+ getLocatorDetails(locator));
			log.error("stack trace is" + we);
			assert false : "Time out exception is occurred";

		} catch (InvalidSelectorException ie) {
			log.error("invalid xpath/css");
			assert false : "InvalidSelector Exception is occurred";

		} catch (NoSuchElementException we) {
			log.error("Failed to retrieve the element, check the xpath/css selector!! locator details are"
					+ getLocatorDetails(locator));
			log.error("stack trace is" + we);
			assert false : "WebDriver exception is occurred";

		} catch (StaleElementReferenceException se) {
			if (stale_count <= STALENESS_MAX_RETRY_COUNT) {
				log.info("Staleness is observed and rrtying ");
				getWebElement(locator);
				stale_count++;
			} else {
				log.error("Stale element exception is occurred!!!!");
				log.error("Stack trace is" + se);
			}
		}

		catch (WebDriverException we) {
			log.error("WebDriver exception is occurred" + we);
			log.error("stack trace is" + we);
			assert false : "NoSuchElement Exception is occurred";

		}

		catch (Exception e) {
			log.error("Failed to retrieve the element!! locator details are" + getLocatorDetails(locator));
			log.error("Exception stack trace is:" + e);
			assert false : "Other Exception is occurred";

		} finally {
			stale_count = 1;
		}
		return element;

	}

	public void waitForPageLoad(WebDriver driver) {
		while (true) {
			String page_status = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
			log.info("Loading........".toUpperCase());
			if (page_status.equals("complete")) {
				break;
			}
		}
	}

	private final String getLocatorDetails(By locator) {
		String[] tokens = locator.toString().split(":");
		return tokens[tokens.length - 1].trim();
	}

	public void waitForElementVisibility(WebDriver driver, int timeout, WebElement element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementClickable(WebDriver driver, int timeout, WebElement element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Get List of webelement in String
	 * 
	 * @param element
	 * @return
	 */
	public List<String> convertWebElementListToString(List<WebElement> element) {
		List<String> list = new ArrayList<>();

		for (WebElement e : element) {
			for (int i = 0; i < 10; i++) {
				try {
					list.add(e.getText());
					break;
				} catch (StaleElementReferenceException exeception) {
					System.out.println(exeception.getMessage());
				}
			}

		}
		return list;
	}

	public void clickElement(String locator) {
		driver.findElement(By.xpath(locator)).click();
	}

	public void clearText(String locator) {
		driver.findElement(By.cssSelector(locator)).clear();

	}

	public void clickElementUsingJS(WebDriver driver, WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static String generateRandomString() {
		String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int RANDOM_STRING_LENGTH = 12;

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			Random num = new Random();
			int number = num.nextInt(52);

			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public static void switchToAlert_Accept(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		org.openqa.selenium.Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void clickElement(WebDriver driver, By by) {
		driver.findElement(by).click();

	}
	public static WebElement findElementByCssSelector(WebDriver driver, String selector){
		return driver.findElement(By.cssSelector(selector));
	}
	/*public static void moveToElement(WebDriver driver, By by){
		Actions actions = new Actions(driver);
		actions.moveToElement(by.cssSelector(selector)))

	}*/


}
