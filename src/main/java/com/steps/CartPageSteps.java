package com.steps;

import com.pages.CartPage;
import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CartPageSteps extends BaseSteps {

    public CartPageSteps(WebDriver driver) throws IOException {
        super(driver);
    }

    // 8. Открываем корзину и сравниваем название и цену в колонке "Total" у товара,
    // на соответствие с сохраненными значениями

    @Step
    public CartPageSteps checkName(String nameFromSearchResults) {
        String productNameInCart = onCartResultsPage().productNameInCart().getText();
        System.out.println("Name in cart: " + productNameInCart);
        Assert.assertEquals(nameFromSearchResults, productNameInCart);
        return this;
    }

    @Step
    public CartPageSteps checkPrice(String priceFromSearchResults) {
        String productPriceInCart = onCartResultsPage().priceTotalInCart().getText();
        System.out.println("Price in cart: " + productPriceInCart);
        //assertEquals(expected, actual); - this is a tip
        Assert.assertEquals(priceFromSearchResults, productPriceInCart);
        return this;
    }

    @Step
    public CartPageSteps checkNameAndPrice(Map<String, String> expectedHashMap) {
        String productNameInCart = onCartResultsPage().productNameInCart().getText();
        System.out.println("Name in cart: " + productNameInCart);
        String productPriceInCart = onCartResultsPage().priceTotalInCart().getText();
        System.out.println("Price in cart: " + productPriceInCart);
        Map<String, String> actualHashMap = new HashMap<>();
        actualHashMap.put(productNameInCart, productPriceInCart);
        Assert.assertEquals(expectedHashMap, actualHashMap);
        return this;
    }

    private CartPage onCartResultsPage() {
        return on(CartPage.class);
    }
}
