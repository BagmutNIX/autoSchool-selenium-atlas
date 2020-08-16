package com.steps;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import io.qameta.atlas.webdriver.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

//пока не юзaю этот класс. но я так понимаю, что должен быть всегда общий класс BaseSteps, от которого экстендятся остальнйе Steps
public class BaseSteps {

    public WebDriver driver;

    private Atlas atlas;

    public Actions getActions() {
        return actions;
    }

    public Actions actions;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        atlas = new Atlas(new WebDriverConfiguration(driver));
        //getAtlas().create(getDriver(), pageClass);
        actions = new Actions(driver);
    }

    public <T extends WebPage> T on(Class<T> page) {
        return atlas.create(driver, page);
    }
}
