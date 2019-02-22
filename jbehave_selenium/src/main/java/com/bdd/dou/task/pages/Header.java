package com.bdd.dou.task.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BasePage {

    @FindBy(xpath = "//*[@id='txtGlobalSearch']")
    private WebElement searhField;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Header enterInputText(String inputText) {
        searhField.sendKeys(inputText);
        return this;
    }

    public SearchResult performSearch() {
        searhField.sendKeys(Keys.ENTER);
        return new SearchResult(driver);
    }

    public WebElement getSearhField(){
        return searhField;
    }
}
