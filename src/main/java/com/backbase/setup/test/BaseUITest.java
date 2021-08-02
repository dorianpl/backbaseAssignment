package com.backbase.setup.test;

import com.backbase.setup.driver.BrowserSetup;
import com.backbase.setup.pages.BasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseUITest extends BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;

    @BeforeAll
    public void setupDriver() {
        driver = new BrowserSetup().driverSetup();
        basePage = new BasePage(driver);
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
