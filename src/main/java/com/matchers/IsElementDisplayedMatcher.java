package com.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;


public class IsElementDisplayedMatcher extends TypeSafeMatcher<WebElement> {

    private int timeout = 10;

    public IsElementDisplayedMatcher() {
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
