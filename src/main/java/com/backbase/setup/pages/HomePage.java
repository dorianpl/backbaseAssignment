package com.backbase.setup.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[(contains(@class, 'nav-link active')) and contains(text(), 'Feed')]")
    private WebElement activeFeed;

    public String returnActiveFeedType() {
        return activeFeed.getText();
    }
}
