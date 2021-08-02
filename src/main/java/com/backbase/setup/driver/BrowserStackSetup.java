package com.backbase.setup.driver;

import com.backbase.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackSetup {
    private PropertiesReader propertiesReader = new PropertiesReader("driver.properties");
    private final String AUTOMATE_USERNAME = propertiesReader.readProperty("driver.bs.username");
    private final String AUTOMATE_ACCESS_KEY = propertiesReader.readProperty("driver.bs.accesskey");
    private final String BS_URL = propertiesReader.readProperty("driver.bs.url");
    private final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + BS_URL;
    private String browser = propertiesReader.readProperty("driver.browser");
    WebDriver driver;


    public WebDriver setupBrowserStackDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", browser);
        caps.setCapability("browser_version", "latest");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.141.5");
                {
            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
