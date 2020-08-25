package com.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

public class BaseElementMatchers {
    private BaseElementMatchers() {
    }

    public static Matcher<WebElement> isDisplayed() {
        return IsElementDisplayedMatcher.isDisplayed();
    }

    public static Matcher<WebElement> hasText(String textMatcher) {
        return HasTextMatcherOLD.hasText(textMatcher);
    }

    public static Matcher<WebElement> hasText(String textMatcher, int timeout) {
        return new HasTextMatcherOLD(textMatcher, timeout);
    }
}
