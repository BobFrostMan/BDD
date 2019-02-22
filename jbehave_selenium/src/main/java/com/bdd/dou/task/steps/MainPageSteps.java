package com.bdd.dou.task.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import com.bdd.dou.task.pages.Header;
import com.bdd.dou.task.pages.SearchResult;

/**
 * Page object representation of DOU main page
 *
 * @see <a href="https://dou.ua/">https://dou.ua/</a>
 */
public class MainPageSteps extends BaseSteps {

    private SearchResult searchResult() {
        return new SearchResult(driver);
    }

    private Header header() {
        return new Header(driver);
    }

    @Given("open main page")
    public void openPage() {
        driver.get("https://dou.ua/");
    }

    @When("user enters '$text' into header search")
    public void userEnterTextintoHeader(String text) {
        header().enterInputText(text);
    }

    @When("user click search button")
    public void userClickSearchButton() {
        header().performSearch();
    }

    @Then("result is displayed")
    public void resultIsDisplayed() {
        Assert.assertTrue("Result wasn't found", searchResult().getSearchResultFindText().isDisplayed());
    }
}
