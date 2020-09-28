import com.core.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "This test just demonstrates the usage of Properties:")
    public void checkLogin() {

        homePageSteps
                .openHomePage()
                .login();
    }
}
