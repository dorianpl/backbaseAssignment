package com.backbase.setup.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class BrowserDimensions {

    /**
     * Set browser dimensions
     *
     * @param driver WebDriver
     * @param width  width of the page
     * @param height height
     */
    public void setBrowserDimensions(WebDriver driver, int width, int height) {
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }
}
