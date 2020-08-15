package com.blocks;

import com.matchers.IsElementDisplayedMatcher;
import io.qameta.atlas.webdriver.AtlasWebElement;

import io.qameta.atlas.webdriver.extension.FindBy;


public interface Product extends AtlasWebElement<Product> {

    //name on search results page: //*[@id='center_column']//*[@class='product-name']
    //name on search results page option2:
    //*[@id='center_column']//div[@class='product-container']//a[@class='product-name']

    @FindBy(".//a[@class='product-name']")
    AtlasWebElement productName();

    //old price on search results page: //*[@id='center_column']//div[1]/*[@class='old-price product-price']
    //actual price on search results page: //*[@id='center_column']//div[1]/*[@class='price product-price']
    //*[@id='center_column']//div[1][@class='content_price']/*[@class='price product-price']

    //old price on search results page option2:
    //*[@id='center_column']//div[1][@class='content_price']/span[@class='old-price product-price']

    @FindBy(".//div[1]/*[@class='old-price product-price']")
    AtlasWebElement productPriceOld();

    @FindBy(".//div[1]/*[@class='price product-price']")
    AtlasWebElement productPriceActual();

    default Double getPrice() {
        //Double productPrice;
        AtlasWebElement productPriceMiddle;
        try {
            productPriceMiddle = productPriceOld();
        } catch (Exception ex) {
            productPriceMiddle = productPriceActual();
        }
        Double productPrice = Double.valueOf(productPriceMiddle.getText().replace("$", ""));
        return productPrice;
    }
}