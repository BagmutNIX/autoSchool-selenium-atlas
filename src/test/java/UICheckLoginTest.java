import com.core.BaseTest;
import com.steps.HomePageSteps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UICheckLoginTest extends BaseTest {

    @Test(description = "This test just demonstrates the usage of Properties:")
    public void checkLogin() throws IOException {

        HomePageSteps homePageSteps = new HomePageSteps(driver);

        homePageSteps
                .openHomePage()
                .login();
    }
}
