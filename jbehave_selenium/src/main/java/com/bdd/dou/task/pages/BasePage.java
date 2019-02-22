package com.bdd.dou.task.pages;

import org.openqa.selenium.WebDriver;

/**
 * Base page class, the holder for webdriver.
 * Provides additional functionality for com.bdd.dou.dou.task.pages
 */
public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
