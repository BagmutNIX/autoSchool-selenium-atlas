package com.pages;

import com.blocks.Product;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface SearchResultsPage extends WebPage {

    @FindBy("//span[@class='lighter']")
    AtlasWebElement searchLabel();

    @FindBy("//div[@id='uniform-selectProductSort']")
    AtlasWebElement sortDropdown();

    @FindBy("//select[@id='selectProductSort']/option[@value='price:desc']")
    AtlasWebElement sortPriceDesc();

    @FindBy("//*[@id='center_column']//div[@class='product-container']")
    ElementsCollection<Product> productList();

    @FindBy("//*[@id='center_column']//div[1][@class='content_price']")
    ElementsCollection<Product> priceList();

    @FindBy("//a[@title='Add to cart']")
    AtlasWebElement addToCartBtn();

    @FindBy("//a[@title='Proceed to checkout']")
    AtlasWebElement proceedToCheckoutBtn();

    @FindBy("//*[@id='center_column']//*[@class='product-name']")
    AtlasWebElement productName();

    @FindBy("//*[@id='center_column']//div[1]/*[@class='price product-price']")
    AtlasWebElement actualPrice();

    @FindBy("//*[@id='center_column']//div[1]/*[@class='old-price product-price']")
    AtlasWebElement oldPrice();

   // @FindBy("id('s-results-list-atf')//li[contains(@id,'result')]")
   // ExtendedList<SearchResult> searchResultList();
}
