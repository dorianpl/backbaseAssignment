package com.backbase.setup.driver;

import com.backbase.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalBrowserSetup {

    private PropertiesReader propertiesReader = new PropertiesReader("driver.properties");
    public WebDriver driver;

    public WebDriver localBrowser() {
        String browserPath;
        switch (propertiesReader.readProperty("driver.browser").toLowerCase()) {
            case ("chrome"):
                browserPath = System.getProperty("user.dir") + propertiesReader.readProperty("driver.local.chrome");
                System.setProperty("webdriver.chrome.driver", browserPath);
                try {
                    driver = new ChromeDriver();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ("firefox"):
                browserPath = System.getProperty("user.dir") + propertiesReader.readProperty("driver.local.gecko");
                System.setProperty("webdriver.gecko.driver", browserPath);
                try {
                    driver = new FirefoxDriver();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ("edge"):
                browserPath = System.getProperty("user.dir") + propertiesReader.readProperty("driver.local.edge");
                System.setProperty("webdriver.edge.driver", browserPath);
                try {
                    driver = new EdgeDriver();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }
}
