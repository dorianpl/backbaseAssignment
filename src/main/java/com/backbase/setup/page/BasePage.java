package com.backbase.setup.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openMainUrl(String url, String username, String password) {
        openMainUrl(addCredentialsToUrl(url, username, password));
    }

    public void openMainUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private String addCredentialsToUrl(String url, String username, String password) {
        return url.replace("https://", "https://" + username + ":" + password + "@");
    }
}
