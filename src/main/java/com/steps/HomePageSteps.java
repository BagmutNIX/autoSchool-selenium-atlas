package com.steps;

import com.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.matchers.BaseElementMatchers.hasText;

public class HomePageSteps extends BaseSteps {

    public HomePageSteps(WebDriver driver) throws IOException {
        super(driver);
    }

    @Step
    public HomePageSteps openHomePage() {
        //baseUrl = "http://automationpractice.com/index.php";
        driver.get(baseUrl);
        return this;
    }

    @Step
    public HomePageSteps checkTextOnTabs() {
        onHomePage().womenTab().should(hasText("WOMEN"));
        onHomePage().dressesTab().should(hasText("DRESSES"));
        onHomePage().tshirtsTab().should(hasText("T-SHIRTS"));
        return this;
    }

    @Step
    public SearchResultsPageSteps enterQueryToSearchInput(String query) throws IOException {
        onHomePage().searchInput().sendKeys(query);
        onHomePage().searchBtn().click();
        return new SearchResultsPageSteps(driver);
    }

    private HomePage onHomePage() {
        return on(HomePage.class);
    }
}

