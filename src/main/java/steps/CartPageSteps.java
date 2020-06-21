package steps;

import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;

import io.qameta.atlas.core.Atlas;


public class CartPageSteps {

    private WebDriver driver;

    private Atlas atlas;

    public CartPageSteps(WebDriver driver) {
        this.driver = driver;
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

    private CartPage onCartResultsPage() {
        return atlas.create(driver, CartPage.class);
    }

}
