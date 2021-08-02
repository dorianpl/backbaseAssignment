package com.backbase.setup.driver;

import com.backbase.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridSetup {

    WebDriver driver;
    private DesiredCapabilities capability;
    private PropertiesReader propertiesReader = new PropertiesReader("driver.properties");
    private String browserName = propertiesReader.readProperty("driver.browser").toLowerCase();
    private String seleniumGridUrl = propertiesReader.readProperty("driver.selenium.grid.url");

    public WebDriver setupSeleniumGridDriver() {
        switch (browserName) {
            case ("chrome"):
                ChromeOptions chromeOptions = new ChromeOptions();
                capability = DesiredCapabilities.chrome();
                capability.setBrowserName(browserName);
                capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                try {
                    driver = new RemoteWebDriver(new URL(seleniumGridUrl), capability);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case ("firefox"):
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                capability = DesiredCapabilities.firefox();
                capability.setBrowserName(browserName);
                capability.setCapability(ChromeOptions.CAPABILITY, firefoxOptions);
                try {
                    driver = new RemoteWebDriver(new URL(seleniumGridUrl), capability);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }
}
