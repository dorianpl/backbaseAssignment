package com.backbase.setup.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage extends BasePage {

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'banner')]//h1")
    private WebElement articleTitle;

    @FindBy(xpath = "//div[contains(@class, 'banner')]//a[contains(@class, 'author')]")
    private WebElement topArticleAuthor;

    @FindBy(xpath = "//div[contains(@class, 'article-content')]//p")
    private WebElement articleContent;

    @FindBy(xpath = "//div[contains(@class, 'banner')]//a[contains(@class, 'btn-outline-secondary')]")
    private WebElement topEditArticleButton;

    @FindBy(xpath = "//div[contains(@class, 'banner')]//button[contains(@class, 'btn-outline-danger')]")
    private WebElement topDeleteArticleButton;

    public String getArticleTitle() {
        return articleTitle.getText();
    }

    public String getArticleAuthor() {
        return topArticleAuthor.getText();
    }

    public String getArticleContent() {
        return articleContent.getText();
    }

    public boolean topEditArticleButtonIsVisible() {
        return topEditArticleButton.isDisplayed();
    }

    public boolean topDeleteArticleButtonIsVisible() {
        return topDeleteArticleButton.isDisplayed();
    }

}
