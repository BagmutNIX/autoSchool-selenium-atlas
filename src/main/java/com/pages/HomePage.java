package com.pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface HomePage extends WebPage {

    @FindBy("//input[@id='search_query_top']")
    AtlasWebElement searchInput();

    @FindBy("//button[@name='submit_search']")
    AtlasWebElement searchBtn();

    @FindBy("//a[@title='Women']")
    AtlasWebElement womenTab();

    @FindBy("//div[@id='block_top_menu']/ul/li[2]/a[@title='Dresses']")
    AtlasWebElement dressesTab();

    @FindBy("//li[3]/a[@title='T-shirts']")
    AtlasWebElement tshirtsTab();
}
