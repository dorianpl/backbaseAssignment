package com.backbase.setup.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditorPage extends BasePage {

    public EditorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@formcontrolname, 'title')]")
    private WebElement articleTitle;

    @FindBy(xpath = "//input[contains(@formcontrolname, 'description')]")
    private WebElement articleDescription;

    @FindBy(xpath = "//textarea[contains(@formcontrolname, 'body')]")
    private WebElement articleBody;

    @FindBy(xpath = "//input[contains(@placeholder, 'tags')]")
    private WebElement articleTags;

    @FindBy(xpath = "//button[contains(text(), 'Publish Article')]")
    private WebElement publishArticleButton;

    public EditorPage inputArticleTitle(String title) {
        articleTitle.sendKeys(title);
        return this;
    }

    public EditorPage inputArticleDescription(String description) {
        articleDescription.sendKeys(description);
        return this;
    }

    public EditorPage inputArticleBody(String body) {
        articleBody.sendKeys(body);
        return this;
    }

    public EditorPage inputArticleTags(String tags) {
        articleTags.sendKeys(tags);
        return this;
    }

    public ArticlePage clickPublishButton() {
        publishArticleButton.click();
        return new ArticlePage(driver);
    }
}
