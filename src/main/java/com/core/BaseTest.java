package com.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod()
    public void setUp() {
        driver = new ChromeDriver();
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
