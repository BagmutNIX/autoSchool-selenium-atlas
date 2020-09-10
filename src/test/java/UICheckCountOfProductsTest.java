import com.core.BaseTest;
import com.steps.HomePageSteps;
import com.steps.SearchResultsPageSteps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UICheckCountOfProductsTest extends BaseTest {
    @Test(description = "This test just demonstrates working of CheckElementsCountMatcher:")
    public void checkCountInProductList() throws IOException {

        HomePageSteps homePageSteps = new HomePageSteps(driver);
        SearchResultsPageSteps searchResultsPageSteps = new SearchResultsPageSteps(driver);

        homePageSteps
                .openHomePage()
                .enterQueryToSearchInput("Summer");
        searchResultsPageSteps
                .checkCountOfResults();
    }
}
