package com.backbase.setup.driver;

import com.backbase.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BrowserSetup {
    private WebDriver driver;
    private WebDriverWait wait;
    private PropertiesReader propertiesReader = new PropertiesReader("driver.properties");

    /**
     * Setups correct version of driver and sets up implicit timeouts
     *
     * @return WebDriver
     */
    public WebDriver driverSetup() {
        String platform = propertiesReader.readProperty("driver.platform");
        switch (platform.toLowerCase()) {
            case ("local"):
                localBrowserSetup();
                break;
            case ("bs"):
                bsBrowserSetup();
                break;
            case ("grid"):
                gridBrowserSetup();
                break;
        }
        driver.manage().timeouts()
                .implicitlyWait(Integer.parseInt(propertiesReader.readProperty("driver.waits.timeout")),
                        TimeUnit.SECONDS);
        return driver;
    }

    /**
     * Setup local driver
     *
     * @return WebDriver
     */
    private WebDriver localBrowserSetup() {
        driver = new LocalBrowserSetup().localBrowser();
        waitForInitialization();
        setWindowSize();
        return driver;
    }

    /**
     * Setup BrowserStack driver
     *
     * @return WebDriver
     */
    private WebDriver bsBrowserSetup() {
        driver = new BrowserStackSetup().setupBrowserStackDriver();
        waitForInitialization();
        return driver;
    }

    /**
     * Setup SeleniumGrid driver
     *
     * @return WebDriver
     */
    private WebDriver gridBrowserSetup() {
        driver = new SeleniumGridSetup().setupSeleniumGridDriver();
        waitForInitialization();
        return driver;
    }

    /**
     * Set driver initialization timeout from driver.properties
     */
    private void waitForInitialization() {
        wait = new WebDriverWait(driver,
                Integer.parseInt(propertiesReader.readProperty("driver.waits.initialization")));
    }

    /**
     * Set initial window size
     */
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
