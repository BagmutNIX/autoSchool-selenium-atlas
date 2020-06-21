package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import io.qameta.atlas.core.Atlas;

import static matchers.BaseElementMatchers.isDisplayed;

public class HomePageSteps {

    private WebDriver driver;

    private Atlas atlas;

    public HomePageSteps(WebDriver driver) {
        this.driver = driver;
    }

    //private String query;

    //public String getQuery() {
    //return query;
    //}

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
