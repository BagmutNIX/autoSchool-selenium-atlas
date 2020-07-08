package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface BasePage extends WebPage {
    @FindBy("//input[@id='search_query_top']")
    AtlasWebElement searchInput();

    @FindBy("//button[@name='submit_search']")
    AtlasWebElement searchBtn();
}
