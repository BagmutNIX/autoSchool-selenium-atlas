package steps;

import blocks.Product;
import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static matchers.BaseElementMatchers.isDisplayed;

public class SearchResultsPageSteps {

    private WebDriver driver;

    //public Atlas getAtlas() { return atlas; }

    private Atlas atlas;

    public Actions getActions() {
        return actions;
    }

    private Actions actions;

    public SearchResultsPageSteps(WebDriver driver) {
        this.driver = driver;
        atlas = new Atlas(new WebDriverConfiguration(driver));
        //getAtlas().create(getDriver(), pageClass);
        actions = new Actions(driver);
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

    // 7. добавляем его в корзину
    @Step
    public CartPageSteps addToCart() {
        Actions action = new Actions(driver);
        List<Product> productList = onSearchResultsPage().productList();
        action.moveToElement(productList.get(0)).moveToElement(onSearchResultsPage().addToCartBtn()).click().build().perform();
        return new CartPageSteps(driver);
    }

    private SearchResultsPage onSearchResultsPage() {
        return atlas.create(driver, SearchResultsPage.class);
    }
}
