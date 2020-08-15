import com.core.BaseTest;
//import org.junit.Test;


import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.steps.HomePageSteps;
import com.steps.SearchResultsPageSteps;

import java.util.HashMap;
import java.util.Map;

public class UISearchSortCartTest extends BaseTest {
    @Test(dataProvider = "searchQuery")
    public void searchTest(String query) throws InterruptedException {
        // 1. Открываем сайт http://automationpractice.com/
        driver.get(baseUrl);
        //BaseSteps baseSteps = new BaseSteps(driver);
        HomePageSteps homePageSteps = new HomePageSteps(driver);
        SearchResultsPageSteps searchResultsPageSteps = new SearchResultsPageSteps(driver);

        System.out.println(query);

        Map<String, String> actualHashMap = new HashMap<>();

        homePageSteps
                // 2. В поле поиска вводим ключевое слово query и нажимаем значок поиска (лупу)
                .enterQueryToSearchInput(query)
                // 3. Проверяем, что над списком продуктов в надписи 'SEARCH' отображается наш поисковый запрос
                .checkSearchLabel(query)
                // 4. Открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
                .sortByPriceDesc()
                // 5. Проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт
                // по старой цене - если у товара есть скидка, нужно смотреть на старую цену)
                .checkSortPricesDesc()
                .getNameAndPriceOfFirstproduct(actualHashMap)
                //String expectedName = searchResultsPageSteps.getNameOfFirstproduct();
                //String expectedPrice = searchResultsPageSteps.getPriceOfFirstproduct();
                .addToCart()
                .checkNameAndPrice(actualHashMap);
        System.out.println("Expected Name: " + actualHashMap.toString());
    }

    @DataProvider(name = "searchQuery")
    public Object[] searchQuery() {
        return new String[]{"Summer", "t-shirt", "Dress"};
    }
}
