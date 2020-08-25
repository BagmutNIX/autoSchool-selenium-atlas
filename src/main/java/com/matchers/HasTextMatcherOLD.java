package com.matchers;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class HasTextMatcherOLD extends TypeSafeMatcher<WebElement> {
    private int timeout = 10;
    private String textMatcher;

    public HasTextMatcherOLD(String textMatcher) {
        this.textMatcher = textMatcher;
    }

    public HasTextMatcherOLD(String textMatcher, int timeout) {
        this.textMatcher = textMatcher;
        this.timeout = timeout;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        long waitUntil = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeout);
        boolean matched = false;
        return item.getText().contains(textMatcher);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element text ").appendText(textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("text of element ").appendValue(item).appendText(" was ")
                .appendValue(item.getText()).appendText(" while waiting ").appendValue(timeout).appendText(" seconds.");
    }

    @Factory
    public static Matcher<WebElement> hasText(final String textMatcher) {
        return new HasTextMatcherOLD(textMatcher);
    }

    @Factory
    public static Matcher<WebElement> hasText(final String textMatcher, int timeout) {
        return new HasTextMatcherOLD(textMatcher, timeout);
    }
}
