package com.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

public class BaseElementMatchers {
    private BaseElementMatchers() {
    }

    public static Matcher<WebElement> isDisplayed() {
        return IsElementDisplayedMatcher.isDisplayed();
    }

    public static Matcher<WebElement> isDisplayed(int timeout) {
        return new IsElementDisplayedMatcher(timeout);
    }

    public static Matcher<WebElement> hasText(String textMatcher) {
        return HasTextMatcher.hasText(textMatcher);
    }

    public static Matcher<WebElement> hasText(String textMatcher, int timeout) {
        return new HasTextMatcher(textMatcher, timeout);
    }
}
