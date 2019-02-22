package com.bdd.dou.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult extends BasePage {

    @FindBy(xpath = "//*[contains(text(),'Найдено результат')]")
    private WebElement searchResultFindText;

    @FindBy(xpath = "//*[contains(@class,'webResult')]")
    private WebElement singleSearchResult;

    @FindBy(xpath = "//*[contains(text(),'Результатов нет')]")
    private WebElement noResultText;

    public SearchResult(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSearchResultFindText() {
        return searchResultFindText;
    }

    public WebElement getSingleSearchResult() {
        return singleSearchResult;
    }

    public WebElement getNoResultText() {
        return noResultText;
    }
}
