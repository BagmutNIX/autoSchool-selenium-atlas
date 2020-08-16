package com.steps;

import com.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePageSteps extends BaseSteps {

    //protected String baseUrl;

    public HomePageSteps(WebDriver driver) throws IOException {
        super(driver);
    }

    // 1. Открываем сайт http://automationpractice.com/
    @Step
    public HomePageSteps openHomePage() {
        //baseUrl = "http://automationpractice.com/index.php";
        driver.get(baseUrl);
        return this;
    }

    // 2. В поле поиска вводим ключевое слово query и нажимаем значок поиска (лупу)
    @Step
    public SearchResultsPageSteps enterQueryToSearchInput(String query) throws IOException {
        //this.query = query;
        onHomePage().searchInput().sendKeys(query);
        onHomePage().searchBtn().click();
        return new SearchResultsPageSteps(driver);
    }

    private HomePage onHomePage() {
        return on(HomePage.class);
    }
}

