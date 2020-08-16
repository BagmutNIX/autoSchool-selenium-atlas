import com.core.BaseTest;
import com.steps.HomePageSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UISearchSortCartTest extends BaseTest {
    @Test(dataProvider = "searchQuery")
    public void searchTest(String query) throws InterruptedException, IOException {

        HomePageSteps homePageSteps = new HomePageSteps(driver);

        System.out.println(query);

        Map<String, String> expectedHashMap = new HashMap<>();

        homePageSteps
                // 1. Открываем сайт http://automationpractice.com/
                .openHomePage()
                // 2. В поле поиска вводим ключевое слово query и нажимаем значок поиска (лупу)
                .enterQueryToSearchInput(query)
                // 3. Проверяем, что над списком продуктов в надписи 'SEARCH' отображается наш поисковый запрос
                .checkSearchLabel(query)
                // 4. Открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
                .sortByPriceDesc()
                // 5. Проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт
                // по старой цене - если у товара есть скидка, нужно смотреть на старую цену)
                .checkSortPricesDesc()
                .getNameAndPriceOfFirstproduct(expectedHashMap)
                // 6. Добавляем первый товар в корзину и проверяем название и цену товара в корзине
                .addToCart()
                .checkNameAndPrice(expectedHashMap);
        System.out.println("Expected Product: " + expectedHashMap.toString());
    }

    @DataProvider(name = "searchQuery")
    public Object[] searchQuery() {
        return new String[]{"Summer", "t-shirt", "Dress"};
    }
}
