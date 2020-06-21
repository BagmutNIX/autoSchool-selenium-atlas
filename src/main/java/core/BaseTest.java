package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod()
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "http://automationpractice.com/index.php";
        //baseUrl = "http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=summer&submit_search=";
        driver.manage().window().maximize();

        // Неявное ожидание (Implicit Waits)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Закрываем браузер (все окна)
        driver.quit();
    }
}
