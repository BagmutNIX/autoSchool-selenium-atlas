package com.blocks;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface Product extends AtlasWebElement<Product> {

    @FindBy(".//a[@class='product-name']")
    AtlasWebElement productName();

    @FindBy(".//div[1]/*[@class='old-price product-price']")
    AtlasWebElement productPriceOld();

    @FindBy(".//div[1]/*[@class='price product-price']")
    AtlasWebElement productPriceActual();


    default Double getPrice() {
        //Double productPrice;
        String priceMidle; //Пыталась сделать через AtlasWebElement productPriceMiddle, но не выщло, так и не поняла, почему именно
        try {
            priceMidle = productPriceOld().getText();
        } catch (Exception ex) {
            priceMidle = productPriceActual().getText();
        }
        return Double.valueOf(priceMidle.replace("$", ""));
    }
}