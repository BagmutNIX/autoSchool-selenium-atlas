package com.matchers;

import io.qameta.atlas.webdriver.ElementsCollection;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CheckElementsCountMatcher extends TypeSafeMatcher<ElementsCollection> { //extends FeatureMatcher<ElementsCollection>

    private int expectedSize;

    public CheckElementsCountMatcher(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    protected boolean matchesSafely(ElementsCollection elementsCollection) {
        //Assert.assertEquals(elementsCollection.size(), expectedSize);
        //List<ElementsCollection> list = elementsCollection;
        //list.getSize().equals(expectedSize);
        //list.size()
        // size.equal
        return elementsCollection.size() == expectedSize;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected unfortunately is ").appendValue(expectedSize);
    }

    @Override
    public void describeMismatchSafely(ElementsCollection actualCollection, Description mismatchDescription) {
        mismatchDescription.appendText("But Actual was ").appendValue(actualCollection.size());
    }

    @Factory
    static Matcher<ElementsCollection> checkCollectionCount(int size) {
        return new CheckElementsCountMatcher(size);
    }
}
