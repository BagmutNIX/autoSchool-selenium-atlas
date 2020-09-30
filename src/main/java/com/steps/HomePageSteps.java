package com.steps;

import com.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.matchers.BaseElementMatchers.hasText;
import static com.properties.MainProperties.props;

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
        onHomePage().tshirtsTab().should(hasText("T-SHIRT"));
        return this;
    }

    @Step
    public HomePageSteps login() {
        onHomePage().loginBtn().click();
        onHomePage().loginInput().sendKeys(props.user1Login());
        onHomePage().paswdInput().sendKeys(props.user1Password());
        onHomePage().submitLoginBtn().click();
        onHomePage().userName().should(hasText(props.user1Name()));
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

