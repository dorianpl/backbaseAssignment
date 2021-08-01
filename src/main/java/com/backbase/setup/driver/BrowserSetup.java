package com.backbase.setup.driver;

import com.backbase.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserSetup {
    private WebDriver driver;
    private WebDriverWait wait;
    private PropertiesReader propertiesReader = new PropertiesReader("driver.properties");

    public WebDriver driverSetup() {
        localBrowserSetup();
        return driver;
    }

    private WebDriver localBrowserSetup() {
        driver = new LocalBrowserSetup().localBrowser();
        waitForInitialization();
        setWindowSize();
        return driver;
    }

    private void waitForInitialization() {
        wait = new WebDriverWait(driver, 60);
    }

    private void setWindowSize() {
        String height = propertiesReader.readProperty("driver.browser.height");
        String width = propertiesReader.readProperty("driver.browser.width");
        if (height.isEmpty() || width.isEmpty()) {
            driver.manage().window().maximize();
        } else {
            BrowserDimensions dimensions = new BrowserDimensions();
            dimensions.setBrowserDimensions(driver, Integer.parseInt(width), Integer.parseInt(height));
        }
    }
}
