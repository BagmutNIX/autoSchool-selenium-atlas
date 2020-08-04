package com.steps;

import com.pages.BasePage;
import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import io.qameta.atlas.core.Atlas;

import static com.matchers.BaseElementMatchers.isDisplayed;

public class HomePageSteps {

    private WebDriver driver;

    private Atlas atlas;

    public Actions getActions() {
        return actions;
    }

    private Actions actions;

    public HomePageSteps(WebDriver driver) {
        this.driver = driver;
        atlas = new Atlas(new WebDriverConfiguration(driver));
        //getAtlas().create(getDriver(), pageClass);
        actions = new Actions(driver);
    }


    // 2. В поле поиска вводим ключевое слово query и нажимаем значок поиска (лупу)
    @Step
    public SearchResultsPageSteps enterQueryToSearchInput(String query) {
        //this.query = query;
        onHomePage().searchInput().sendKeys(query);
        onHomePage().searchBtn().click();
        return new SearchResultsPageSteps(driver);
    }

    private BasePage onHomePage() {
        return atlas.create(driver, BasePage.class);
    }
}
