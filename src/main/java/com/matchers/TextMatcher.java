
package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class TextMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private String expectedText;

    private TextMatcher(String expectedText) {
        this.expectedText = expectedText;
    }

    @Override
    protected boolean matchesSafely(AtlasWebElement atlasWebElement) {
        return atlasWebElement.getText().equals(expectedText);
    }

    @Override
    public void describeTo(Description description) {
        //description.appendValue(expectedText);
        //appendText("text is ").
    }

    @Override
    protected void describeMismatchSafely(AtlasWebElement item, Description mismatchDescription) {
        mismatchDescription.appendValue(item.getText());
        //.appendText("Actual text was ")
    }

    @Factory
    static Matcher<AtlasWebElement> hasText(final String expectedText) {
        return new TextMatcher(expectedText);
    }

}
