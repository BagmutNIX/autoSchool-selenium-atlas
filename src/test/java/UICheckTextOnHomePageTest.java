import com.core.BaseTest;
import com.steps.HomePageSteps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UICheckTextOnHomePageTest extends BaseTest {

    @Test(description = "This test just demonstrates working of TextMatcher:")
    public void checkTextOnHomePage() throws IOException {

        HomePageSteps homePageSteps = new HomePageSteps(driver);

        homePageSteps
                .openHomePage()
                .checkTextOnTabs();
    }
}
