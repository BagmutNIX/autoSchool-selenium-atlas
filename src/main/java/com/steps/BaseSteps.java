package com.steps;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import io.qameta.atlas.webdriver.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.Properties;

//пока не юзaю этот класс. но я так понимаю, что должен быть всегда общий класс BaseSteps, от которого экстендятся остальнйе Steps
public class BaseSteps {

    public WebDriver driver;

    public String baseUrl;

    private Atlas atlas;

    public Actions actions;

    public Actions getActions() {
        return actions;
    }

    public BaseSteps(WebDriver driver) throws IOException {
        this.driver = driver;
        Properties properties = new Properties();
        // вычитываем файл *.base.properties из директории <root>/src/main/java/resources
        properties.load(this.getClass().getClassLoader().getResourceAsStream("base.properties"));
        baseUrl = properties.getProperty("baseUrl");
        atlas = new Atlas(new WebDriverConfiguration(driver));
        //getAtlas().create(getDriver(), pageClass);
        actions = new Actions(driver);
    }

    public <T extends WebPage> T on(Class<T> page) {
        return atlas.create(driver, page);
    }
}
