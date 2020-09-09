package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import org.hamcrest.*;
import org.testng.Assert;
import java.util.List;

public class CheckCollectionSizeMatcher extends TypeSafeMatcher<ElementsCollection> { //extends FeatureMatcher<ElementsCollection>

    private int expectedSize;

    public CheckCollectionSizeMatcher(int expectedSize) {
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
        return new CheckCollectionSizeMatcher(size);
    }
}
