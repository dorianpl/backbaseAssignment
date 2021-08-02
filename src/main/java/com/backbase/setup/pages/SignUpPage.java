package com.backbase.setup.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@formcontrolname, 'username')]")
    private WebElement username;

    @FindBy(xpath = "//input[contains(@formcontrolname, 'email')]")
    private WebElement email;

    @FindBy(xpath = "//input[contains(@formcontrolname, 'password')]")
    private WebElement password;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private WebElement submit;

    public SignUpPage inputUsername(String name) {
        username.sendKeys(name);
        return this;
    }

    public SignUpPage inputEmail(String mail) {
        email.sendKeys(mail);
        return this;
    }

    public SignUpPage inputPassword(String pass) {
        password.sendKeys(pass);
        return this;
    }

    public HomePage clickSubmitButton() {
        submit.click();
        return new HomePage(driver);
    }
}
