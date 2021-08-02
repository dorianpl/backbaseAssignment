package com.backbase.setup.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'conduit')]")
    protected WebElement mainIcon;

    @FindBy(xpath = "//a[contains(text(), 'Sign up')]")
    protected WebElement signUp;

    @FindBy(xpath = "//a[contains(text(), 'Sign in')]")
    protected WebElement signIn;

    @FindBy(xpath = "//a[contains(text(), 'Home')]")
    protected WebElement home;

    @FindBy(xpath = "//a[contains(text(), 'Settings')]")
    protected WebElement settings;

    @FindBy(xpath = "//a[contains(text(), 'New Article')]")
    protected WebElement newArticle;

    @FindBy(xpath = "//a[contains(@href, 'profile')]")
    protected WebElement profile;

    public void openMainUrl(String url, String username, String password) {
        openMainUrl(addCredentialsToUrl(url, username, password));
        //Needed for Firefox. Page needs to be reloaded to load all page objects. Notice that browser refresh doesn't help.
        openMainUrl(url);
    }

    public void openMainUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public SignInPage openSignInPage() {
        signIn.click();
        return new SignInPage(driver);
    }

    public SignUpPage openSignUpPage() {
        signUp.click();
        return new SignUpPage(driver);
    }

    public HomePage openHomePage() {
        home.click();
        return new HomePage(driver);
    }

    public EditorPage openNewArticlePage() {
        newArticle.click();
        return new EditorPage(driver);
    }

    public HomePage clickMainIcon() {
        mainIcon.click();
        return new HomePage(driver);
    }

    public boolean settingsAreVisible() {
        return settings.isDisplayed();
    }

    public boolean newArticleIsVisible() {
        return newArticle.isDisplayed();
    }

    public boolean profileNameIsVisible() {
        return profile.isDisplayed();
    }

    private String addCredentialsToUrl(String url, String username, String password) {
        return url.replace("https://", "https://" + username + ":" + password + "@");
    }
}
