package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

public class BaseElementMatchers {
    private BaseElementMatchers() {
    }

    public static Matcher<WebElement> isDisplayed() {
        return IsElementDisplayedMatcher.isDisplayed();
    }

    public static Matcher<AtlasWebElement> hasText(String expectedText) {
        return TextMatcher.hasText(expectedText);
    }

    public static Matcher<ElementsCollection> isCountMatch(int size) {
        return CheckElementsCountMatcher.isCountMatch(size);
    }
}
