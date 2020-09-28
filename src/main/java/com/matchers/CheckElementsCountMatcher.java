package com.matchers;

import io.qameta.atlas.webdriver.ElementsCollection;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CheckElementsCountMatcher extends TypeSafeMatcher<ElementsCollection> {

    private int expectedSize;

    public CheckElementsCountMatcher(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    protected boolean matchesSafely(ElementsCollection elementsCollection) {
        return elementsCollection.size() == expectedSize;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected is ").appendValue(expectedSize);
    }

    @Override
    public void describeMismatchSafely(ElementsCollection actualCollection, Description mismatchDescription) {
        mismatchDescription.appendText("but Actual was ").appendValue(actualCollection.size());
    }

    @Factory
    static Matcher<ElementsCollection> isCountMatch(int size) {
        return new CheckElementsCountMatcher(size);
    }
}
