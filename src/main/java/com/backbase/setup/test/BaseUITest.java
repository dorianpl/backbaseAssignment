package com.backbase.setup.test;

import com.backbase.setup.driver.BrowserSetup;
import com.backbase.setup.pages.BasePage;
import com.backbase.setup.pages.HomePage;
import com.backbase.utils.PropertiesReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseUITest extends BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    protected PropertiesReader properties = new PropertiesReader("test.properties");

    @BeforeAll
    public void setupDriver() {
        driver = new BrowserSetup().driverSetup();
        basePage = new BasePage(driver);
    }

    @BeforeEach
    public void startBrowser() {
        String url = properties.readProperty("test.mainUrl");
        String username = properties.readProperty("test.authorisation.username");
        String password = properties.readProperty("test.authorisation.password");
        homePage = new HomePage(driver);
        homePage.openMainUrl(url, username, password);
    }

    @AfterEach
    void clearDriverSession() {
        driver.manage().deleteAllCookies();

        if (driver instanceof WebStorage) {
            WebStorage webStorage = (WebStorage) driver;
            webStorage.getSessionStorage().clear();
            webStorage.getLocalStorage().clear();
        }
    }

    @AfterAll
    public void quitDriver() {
        driver.quit();
    }
}
