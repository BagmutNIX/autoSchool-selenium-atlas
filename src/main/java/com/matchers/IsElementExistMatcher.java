package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class IsElementExistMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private IsElementExistMatcher() {
    }

    @Override
    protected boolean matchesSafely(AtlasWebElement item) {
        try {
            item.findElement(By.xpath("self::*"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element exist");
    }

    @Override
    public void describeMismatchSafely(AtlasWebElement element, Description mismatchDescription) {
        mismatchDescription.appendText("element ").appendValue(element).appendText(" does not exist");
    }

    @Factory
    static Matcher<AtlasWebElement> exists() {
        return new IsElementExistMatcher();
    }

}