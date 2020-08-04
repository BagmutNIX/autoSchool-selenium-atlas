package com.pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface CartPage extends WebPage {

    @FindBy("//td/p[@class='product-name']")
    AtlasWebElement productNameInCart();

    @FindBy("//td[@class='cart_total'][@data-title='Total']")
    AtlasWebElement priceTotalInCart();

    @FindBy("//*[@class='icon-trash']")
    AtlasWebElement trashInCart();
}
