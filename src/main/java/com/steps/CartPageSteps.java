package com.steps;

import com.pages.BasePage;
import com.pages.CartPage;
import io.qameta.allure.Step;

import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.qameta.atlas.core.Atlas;


public class CartPageSteps extends BaseSteps {
   // BaseSteps atlas = new BaseSteps();
    public CartPageSteps(WebDriver driver) {
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
        //assertEquals(expected, actual);
        Assert.assertEquals(priceFromSearchResults, productPriceInCart);
        return this;
    }

    // Удаляем продукт из корзины для продолжения проверки других продуктов
    @Step
    public CartPageSteps deleteProduct() {
        onCartResultsPage().trashInCart().click();
        return this;
    }

    //private CartPage onCartResultsPage() { return atlas.create(driver, CartPage.class);}

    private CartPage onCartResultsPage() { return on(CartPage.class); }

}
