import com.core.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CountOfProductsTest extends BaseTest {
    @Test(description = "This test just demonstrates the usage of CheckElementsCountMatcher:")
    public void checkCountInProductList() throws IOException {

        homePageSteps
                .openHomePage()
                .enterQueryToSearchInput("Summer")
                .checkCountOfResults();
    }
}
