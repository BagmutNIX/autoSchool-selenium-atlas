package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

public class BaseElementMatchers {


    private BaseElementMatchers() {
    }

    public static Matcher<WebElement> isDisplayed() {
        return IsElementDisplayedMatcher.isDisplayed();
    }

    public static Matcher<AtlasWebElement> hasText(String expectedText) {
        return HasTextMatcher.hasText(expectedText);
    }



}
