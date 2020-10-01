
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
        return atlasWebElement.getText().replaceAll("\\p{javaSpaceChar}", " ").equals(expectedText);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("text is ").appendValue(expectedText);
    }

    @Override
    protected void describeMismatchSafely(AtlasWebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("Actual text was ").appendValue(item.getText());
    }

    @Factory
    static Matcher<AtlasWebElement> hasText(final String expectedText) {
        return new TextMatcher(expectedText);
    }
}
