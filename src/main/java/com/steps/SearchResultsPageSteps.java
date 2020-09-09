package com.steps;

import com.blocks.Product;
import com.pages.SearchResultsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.matchers.BaseElementMatchers.isDisplayed;
import static com.matchers.BaseElementMatchers.isCountMatch;

public class SearchResultsPageSteps extends BaseSteps {

    public SearchResultsPageSteps(WebDriver driver) throws IOException {
        super(driver);
    }

    @Step
    public SearchResultsPageSteps checkSearchLabel(String query) {
        String searchLabelText = onSearchResultsPage().searchLabel().getText().replaceAll("\"", "").toLowerCase();
        Assert.assertEquals(searchLabelText, query.toLowerCase());
        return this;
    }

    @Step
    public SearchResultsPageSteps sortByPriceDesc() {
        onSearchResultsPage().sortDropdown().click();
        onSearchResultsPage().sortPriceDesc().click();
        return this;
    }

    @Step
    public SearchResultsPageSteps checkSortPricesDesc() {

        List<Product> productList = onSearchResultsPage().productList();

        List<Double> productPrice;

        productPrice = productList.stream().map(Product::getPrice).collect(Collectors.toList());

        List<Double> productPriceSorted = new ArrayList<>(productPrice);
        Collections.sort(productPriceSorted, Collections.reverseOrder());

        Assert.assertEquals(productPriceSorted, productPrice);

        return this;
    }

    @Step
    public SearchResultsPageSteps getNameAndPriceOfFirstproduct(Map<String, String> nameAndPrice) {
        List<Product> productList = onSearchResultsPage().productList();
        String nameText = productList.get(0).should(isDisplayed()).productName().getText();
        String priceText = productList.get(0).should(isDisplayed(), 470).productPriceActual().getText();
        nameAndPrice.put(nameText, priceText);
        return this;
    }

    @Step
    public SearchResultsPageSteps checkCountOfResults() {
        onSearchResultsPage().productList().should(isCountMatch(5));
        return this;
    }

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
