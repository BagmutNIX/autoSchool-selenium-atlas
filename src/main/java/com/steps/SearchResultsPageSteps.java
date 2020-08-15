package com.steps;

import com.blocks.Product;
import com.pages.SearchResultsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

import static com.matchers.BaseElementMatchers.isDisplayed;

public class SearchResultsPageSteps extends BaseSteps {
    public SearchResultsPageSteps(WebDriver driver) {
        super(driver);
    }

    //private HomePageSteps query;

    // 3. Проверяем, что над списком продуктов в надписи 'SEARCH' отображается наш поисковый запрос
    @Step
    public SearchResultsPageSteps checkSearchLabel(String query) {
        String searchLabelText = onSearchResultsPage().searchLabel().getText().replaceAll("\"", "").toLowerCase();
        Assert.assertEquals(searchLabelText, query.toLowerCase());
        return this;
    }

    // 4. открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
    @Step
    public SearchResultsPageSteps sortByPriceDesc() {
        onSearchResultsPage().sortDropdown().click();
        onSearchResultsPage().sortPriceDesc().click();
        return this;
    }

    // 5. проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт по старой
    // цене - если у товара есть скидка, нужно смотреть на старую цену)

    @Step
    public SearchResultsPageSteps checkSortPricesDesc() {

        List<Product> productList = onSearchResultsPage().productList();

        List<Double> productPrice = new ArrayList<>();

        productPrice = productList.stream().map(Product::getPrice).collect(Collectors.toList());

        System.out.println("Product Prices: " + productPrice);

        List<Double> productPriceSorted = new ArrayList<>(productPrice);
        Collections.sort(productPriceSorted, Collections.reverseOrder());

        System.out.println("Sorted prices:");
        for (int i = 0; i < productPriceSorted.size(); i++)
            System.out.println(productPriceSorted.get(i));

        Assert.assertEquals(productPrice, productPriceSorted);

        return this;
    }

    // 6. Берем первый из найденных товаров и запоминаем его полное название и цену
    @Step
    public String getNameOfFirstproduct() {
        List<Product> productList = onSearchResultsPage().productList();
        //System.out.println("Product list size: " + productList.size());
        String nameText = productList.get(0).should(isDisplayed()).productName().getText();
        return nameText;
    }

    @Step
    public String getPriceOfFirstproduct() {
        List<Product> productList = onSearchResultsPage().productList();
        String priceText = productList.get(0).should(isDisplayed()).productPriceActual().getText();
        return priceText;
    }

    @Step
    public SearchResultsPageSteps getNameAndPriceOfFirstproduct(Map<String, String> hashMap) {
        List<Product> productList = onSearchResultsPage().productList();
        //Map<String, String> hashMap = new HashMap<>();
        //System.out.println("Product list size: " + productList.size());
        String nameText = productList.get(0).should(isDisplayed()).productName().getText();
        String priceText = productList.get(0).should(isDisplayed()).productPriceActual().getText();
        hashMap.put(nameText, priceText);
        return this;
    }

    // 7. добавляем его в корзину
    @Step
    public CartPageSteps addToCart() {
        Actions action = new Actions(driver);
        List<Product> productList = onSearchResultsPage().productList();
        action.moveToElement(productList.get(0)).moveToElement(onSearchResultsPage().addToCartBtn()).click().build().perform();
        onSearchResultsPage().proceedToCheckoutBtn().click();
        return new CartPageSteps(driver);
    }

    private SearchResultsPage onSearchResultsPage() {
        return on(SearchResultsPage.class);
    }
}
