package com.backbase.setup.test;

import com.backbase.setup.driver.BrowserSetup;
import com.backbase.setup.page.BasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseUITest extends BaseTest {

    public WebDriver driver;
    public BasePage basePage;

    @BeforeAll
    public void setupDriver() {
        driver = new BrowserSetup().driverSetup();
        basePage = new BasePage(driver);
    }

    @AfterAll
    public void quitDriver() {
        driver.quit();
    }
}
