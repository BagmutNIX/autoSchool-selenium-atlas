package com.steps;

import com.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.matchers.BaseElementMatchers.isDisplayed;

public class HomePageSteps extends BaseSteps{

    public HomePageSteps(WebDriver driver) {
        super(driver);
    }

    // 2. В поле поиска вводим ключевое слово query и нажимаем значок поиска (лупу)
    @Step
    public SearchResultsPageSteps enterQueryToSearchInput(String query) {
        //this.query = query;
        onHomePage().searchInput().sendKeys(query);
        onHomePage().searchBtn().click();
        return new SearchResultsPageSteps(driver);
    }

    // private BasePage onHomePage() { return atlas.create(driver, BasePage.class); }

    private HomePage onHomePage() { return on(HomePage.class); }

    }

