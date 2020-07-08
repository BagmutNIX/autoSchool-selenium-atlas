import core.BaseTest;
//import org.junit.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import steps.HomePageSteps;
import steps.SearchResultsPageSteps;

import java.util.ArrayList;
import java.util.List;

public class UISearchSortCartTest extends BaseTest {
    @Test(dataProvider = "searchQuery")
    public void searchTest(String query) throws InterruptedException {
        // 1. Открываем сайт http://automationpractice.com/
        driver.get(baseUrl);
        //BaseSteps baseSteps = new BaseSteps(driver);
        HomePageSteps homePageSteps = new HomePageSteps(driver);
        SearchResultsPageSteps searchResultsPageSteps = new SearchResultsPageSteps(driver);

        System.out.println(query);



        homePageSteps
                // 2. В поле поиска вводим ключевое слово query и нажимаем значок поиска (лупу)
                .enterQueryToSearchInput(query)
                // 3. Проверяем, что над списком продуктов в надписи 'SEARCH' отображается наш поисковый запрос
                .checkSearchLabel(query)
                // 4. Открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
                .sortByPriceDesc()
                // 5. Проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт
                // по старой цене - если у товара есть скидка, нужно смотреть на старую цену)
                .checkSortPricesDesc();

        String expectedName = searchResultsPageSteps.getNameOfFirstproduct();
        String expectedPrice = searchResultsPageSteps.getPriceOfFirstproduct();
        System.out.println("Expected Name: " + expectedName + "; Expected Price: " + expectedPrice);

        searchResultsPageSteps
                .addToCart()
                .checkName(expectedName)
                .checkPrice(expectedPrice)
                .deleteProduct();
    }

    @DataProvider(name = "searchQuery")
    public Object[][] searchQuery() {
        List<String> data = new ArrayList<>();
        data.add("Summer");
        data.add("t-shirt");
        data.add("Dress");

        Object[][] result = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i).split(",");
        }
        System.out.println(result.toString());
        return result;
    }
}
