package com.imaginea.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TestUtils {
    private final Logger log = LogManager.getLogger(TestUtils.class);

    public static String getDriverPath(String browser) {
        String osName = SystemUtils.OS_NAME.toLowerCase();
        String driverName = getDriverName(browser);

        String driverPath = "drivers" + File.separator;
        if (osName.indexOf("win") >= 0) {
            // driverPath += File.separator + driverName + ".exe";
            driverPath = System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + driverName
                    + ".exe";
            System.out.println(driverPath);
        } else if (osName.indexOf("mac") >= 0) {
            driverPath += File.separator + driverName;
        } else {
            driverPath += File.separator + driverName;
        }

        return driverPath;
    }

    private static String getDriverName(String browser) {
        if (browser.toLowerCase().indexOf("chrome") >= 0) {
            return "chromedriver";
        } else if (browser.toLowerCase().indexOf("ie") >= 0) {
            return "IEDriverServer";
        } else {
            return "safaridriver";
        }
    }

    /**
     * Get List of webelement in String
     * 
     * @param element
     * @return
     */
    public static List<String> convertWebElementListToString(List<WebElement> element) {
        List<String> list = new ArrayList<>();
        for (WebElement e : element) {
            list.add(e.getText());
        }
        return list;
    }

}
