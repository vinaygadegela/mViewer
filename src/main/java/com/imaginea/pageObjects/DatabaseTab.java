package com.imaginea.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;

import com.imaginea.utils.TestUtils;
import com.imaginea.utils.UIUtility;

public class DatabaseTab extends UIUtility{

        WebDriver driver;
        Logger log = Logger.getLogger(DatabaseTab.class);

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

        /**
         * Constructor to initialize Database tab
         * 
         * @param driver
         */
        public DatabaseTab(WebDriver driver) {
        		super(driver);
                this.driver = driver;
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
