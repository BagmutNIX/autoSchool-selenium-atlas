package com.core;

import com.steps.HomePageSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;


public abstract class BaseTest {
    protected WebDriver driver;

    public HomePageSteps getHomePageSteps() throws IOException {
        return new HomePageSteps(driver);
    }

    @BeforeMethod()
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
