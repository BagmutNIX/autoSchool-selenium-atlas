import com.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchSortCartTest extends BaseTest {
    @Test(dataProvider = "searchQuery")
    public void searchTest(String query) throws IOException {

        Map<String, String> expectedNameAndPrice = new HashMap<>();

        homePageSteps
                .openHomePage()
                .enterQueryToSearchInput(query)
                .checkSearchLabel(query)
                .sortByPriceDesc()
                .checkSortPricesDesc()
                .getNameAndPriceOfFirstproduct(expectedNameAndPrice)
                .addToCart()
                .checkNameAndPrice(expectedNameAndPrice);
    }

    @DataProvider(name = "searchQuery")
    public Object[] searchQuery() {
        return new String[]{"Summer", "t-shirt", "Dress"};
    }
}
