package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class HasTextMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private String expectedText;

    public HasTextMatcher(String expectedText) {
        this.expectedText = expectedText;
    }

    @Override
    protected boolean matchesSafely(AtlasWebElement atlasWebElement) {
        return atlasWebElement.getText().equals(expectedText);
    }

    @Override
    public void describeTo(Description description) {
    }

    @Override
    protected void describeMismatchSafely(AtlasWebElement item, Description mismatchDescription) {
        super.describeMismatch(item, mismatchDescription);
    }

    @Factory
    static Matcher<AtlasWebElement> hasText(final String expectedText) {
        return new HasTextMatcher(expectedText);
    }

}
