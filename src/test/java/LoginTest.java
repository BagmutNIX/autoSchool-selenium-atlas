import com.core.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test(description = "This test just demonstrates the usage of Properties:")
    public void checkLogin() throws IOException {

        getHomePageSteps()
                .openHomePage()
                .login();
    }
}
