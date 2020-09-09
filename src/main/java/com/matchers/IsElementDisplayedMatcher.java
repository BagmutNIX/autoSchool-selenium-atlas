package com.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class IsElementDisplayedMatcher extends TypeSafeMatcher<WebElement> {

    private int timeout = 10;

    public IsElementDisplayedMatcher() {
    }

    public IsElementDisplayedMatcher(int timeout) {
        this.timeout = timeout;
    }

    @Override
    protected boolean matchesSafely(WebElement item) {
        return item.isDisplayed();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is displayed on page");
    }

    @Factory
    public static Matcher<WebElement> isDisplayed() {
        return new IsElementDisplayedMatcher();
    }

}
