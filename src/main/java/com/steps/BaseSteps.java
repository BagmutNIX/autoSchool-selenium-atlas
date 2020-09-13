package com.steps;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import io.qameta.atlas.webdriver.WebPage;
import main.properties.MainProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class BaseSteps {

    public WebDriver driver;

    public String baseUrl;

    private Atlas atlas;

    private Actions actions;

    public Actions getActions() {
        return actions;
    }

    public BaseSteps(WebDriver driver) throws IOException {
        this.driver = driver;
        baseUrl = MainProperties.props.baseUrl();
        atlas = new Atlas(new WebDriverConfiguration(driver));
        actions = new Actions(driver);
    }

    public <T extends WebPage> T on(Class<T> page) {
        return atlas.create(driver, page);
    }
}
