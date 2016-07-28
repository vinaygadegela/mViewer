package com.imaginea.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;


public class DataBasesTab extends HomePage{

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
        
        private String dbSettingsGear="a[label='%s']+a";
        private String addCollection="div[id='%s_subMenu'] ul>li:nth-child(1)>a";
        private String addGridFSBucket="div[id='%s_subMenu'] ul>li:nth-child(2)>a";
        private String dropDatabase="div[id='%s_subMenu'] ul>li:nth-child(3)>a";
        private String statistics="div[id='%s_subMenu'] ul>li:nth-child(4)>a";
        
        @FindBy(css="#addColDialog [for='name']+input") private WebElement collectionName;
        @FindBy(css="#addColDialog [for='collSize']+input") private WebElement size;
        @FindBy(css="#addColDialog [for='collMaxSize']+input") private WebElement maxDocuments;
        @FindBy(css="#addColDialog #yui-gen2-button") private WebElement submit;
        @FindBy(css="#addColDialog #yui-gen3-button") private WebElement cancel;
        

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
                createDb.click();
                waitForElementVisibility(driver, 20, dbNameTextBox);
                dbNameTextBox.clear();
                dbNameTextBox.sendKeys(dbName);
                submitButton.click();
        }

        public void addCollection(String dbName){
        	clickAddCollection(dbName);
        	
        }
        
        
        public void clickAddCollection(String dbName){
        	String locator=	String.format(dbSettingsGear, dbName);
        	driver.findElement(By.cssSelector(locator)).click();
        }
        /**
         * Get List of DB
         * 
         * @return
         */
        public List<String> getDBList(WebDriver driver) {
                waitForElementVisibility(driver, 20, createDb);
                return convertWebElementListToString(dbNames);
        }

        /**
         * Get Info Message when error message is displayed
         * 
         * @return
         */
        public String getInfoMessage() {
                return infoMessage.getText();
        }
}
