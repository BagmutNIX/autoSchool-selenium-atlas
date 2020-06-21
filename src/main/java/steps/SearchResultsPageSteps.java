package steps;

import blocks.Product;
import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static matchers.BaseElementMatchers.isDisplayed;

public class SearchResultsPageSteps {

    private WebDriver driver;

    private Atlas atlas;

    public SearchResultsPageSteps(WebDriver driver) {
        this.driver = driver;
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

    //public

    @Step
    public SearchResultsPageSteps checkSortPricesDesc() {
        //List<String> productPrice = Collections.singletonList(onSearchResultsPage().productList().toString());
        List<Product> productList = (List<Product>) onSearchResultsPage().productList();
        List<Double> productPrice = new ArrayList<>();

        /* for (int i = 0; i < productList.size(); i++) {
            String price = productList.get(i).should(isDisplayed()).productPriceActual().getText().replace("$", "");
            try {
                price = productList.get(i).should(isDisplayed()).productPriceOld().getText().replace("$", "");
            } catch (Exception ex) {
            }
            productPrice.add(Double.valueOf(price));
        }*/

        productList.forEach(product -> {
            String strPrice = product.should(isDisplayed()).productPriceActual().getText().replace("$", "");
            try {
                strPrice = product.should(isDisplayed()).productPriceOld().getText().replace("$", "");
            } catch (Exception ex) {
            }
            double price = 777.0;
            try {
                price = Double.valueOf(strPrice);
            } catch (Exception ex) {
            }
            productPrice.add(price);
        });

        System.out.println("Saved prices:");
        for (int i = 0; i < productPrice.size(); i++) {
            System.out.println(productPrice.get(i));
        }

        // Collections.copy(productPriceSorted, productPrice);
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
        //List<Product> productList = onSearchResultsPage().productList();
        //action.moveToElement(productList.get(0)).moveToElement(onSearchResultsPage().addToCartBtn()).click().build().perform();
        //action.moveToElement(onSearchResultsPage().productName()).build().perform();
        WebElement product = driver.findElement(By.xpath("//*[@id='center_column']//*[@class='product-name']"));
        action.moveToElement(product).build().perform();
        onSearchResultsPage().addToCartBtn().click();
        onSearchResultsPage().proceedToCheckoutBtn().click();
        //new Actions(this.driver).doubleClick(this.label).perform();
        //new Actions(this.driver).moveToElement(productList.get(0)).perform();
        //click(onSearchResultsPage().addToCartBtn());
        //onSearchResultsPage().addToCartBtn().click();
        return new CartPageSteps(driver);
    }

    private SearchResultsPage onSearchResultsPage() {
        return atlas.create(driver, SearchResultsPage.class);
    }
}
