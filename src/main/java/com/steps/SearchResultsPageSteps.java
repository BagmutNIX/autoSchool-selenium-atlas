package com.steps;

import com.blocks.Product;
import com.pages.SearchResultsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.matchers.BaseElementMatchers.isCountMatch;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.matchers.BaseElementMatchers.isDisplayed;

public class SearchResultsPageSteps extends BaseSteps {

    public SearchResultsPageSteps(WebDriver driver) throws IOException {
        super(driver);
    }

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

        List<Double> productPrice;

        productPrice = productList.stream().map(Product::getPrice).collect(Collectors.toList());

        System.out.println("Product prices: " + productPrice);

        List<Double> productPriceSorted = new ArrayList<>(productPrice);
        Collections.sort(productPriceSorted, Collections.reverseOrder());

        System.out.println("Sorted prices:");
        for (Double aDouble : productPriceSorted) System.out.println(aDouble);

        Assert.assertEquals(productPriceSorted, productPrice);

        assertThat(productPrice, hasSize(4));
        //onSearchResultsPage().productList().should(isCountMatch(4));

        return this;
    }

    // 6. Берем первый из найденных товаров и запоминаем его полное название и цену
    @Step
    public SearchResultsPageSteps getNameAndPriceOfFirstproduct(Map<String, String> nameAndPrice) {
        List<Product> productList = onSearchResultsPage().productList();
        String nameText = productList.get(0).should(isDisplayed()).productName().getText();
        String priceText = productList.get(0).should(isDisplayed(), 470).productPriceActual().getText();
        nameAndPrice.put(nameText, priceText);
        return this;
    }

    // 7. добавляем его в корзину
    @Step
    public CartPageSteps addToCart() throws IOException {
        List<Product> productList = onSearchResultsPage().productList();
        getActions().moveToElement(productList.get(0)).moveToElement(onSearchResultsPage().addToCartBtn()).click().build().perform();
        onSearchResultsPage().proceedToCheckoutBtn().click();
        return new CartPageSteps(driver);
    }

    private SearchResultsPage onSearchResultsPage() {
        return on(SearchResultsPage.class);
    }
}
