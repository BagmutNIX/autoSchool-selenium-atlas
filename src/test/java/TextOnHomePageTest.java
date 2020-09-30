import com.core.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TextOnHomePageTest extends BaseTest {

    @Test(description = "This test just demonstrates the usage of TextMatcher:")
    public void checkTextOnHomePage() throws IOException {

        getHomePageSteps()
                .openHomePage()
                .checkTextOnTabs();
    }
}
