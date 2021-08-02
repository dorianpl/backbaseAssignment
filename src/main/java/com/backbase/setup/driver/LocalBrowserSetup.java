package com.backbase.setup.driver;

import com.backbase.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LocalBrowserSetup {

    private PropertiesReader propertiesReader = new PropertiesReader("driver.properties");
    public WebDriver driver;

    /**
     * Setup local browser according to driver.properties file
     *
     * @return WebDriver
     */
    public WebDriver localBrowser() {
        String browserPath;
        boolean headless = propertiesReader.readProperty("driver.headless").toLowerCase().equals("true");
        switch (propertiesReader.readProperty("driver.browser").toLowerCase()) {
            case ("chrome"):
                browserPath = System.getProperty("user.dir") + propertiesReader.readProperty("driver.local.chrome");
                System.setProperty("webdriver.chrome.driver", browserPath);
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("headless");
                }
                try {
                    driver = new ChromeDriver(chromeOptions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ("firefox"):
                browserPath = System.getProperty("user.dir") + propertiesReader.readProperty("driver.local.gecko");
                System.setProperty("webdriver.gecko.driver", browserPath);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("headless");
                }
                try {
                    driver = new FirefoxDriver(firefoxOptions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ("edge"):
                browserPath = System.getProperty("user.dir") + propertiesReader.readProperty("driver.local.edge");
                System.setProperty("webdriver.edge.driver", browserPath);
                try {
                    // Edge can't be run in headless mode in selenium the latest release version 3.141.59
                    driver = new EdgeDriver();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }
}
