package com.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckElementsCountMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private int expectedSize;

    public CheckElementsCountMatcher(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    protected boolean matchesSafely(AtlasWebElement elementsCollection) {
        //Assert.assertEquals(elementsCollection.getSize(), expectedSize);
        //elementsCollection.stream().count().
        // size.equal*/
        return elementsCollection.getSize().equals(expectedSize);
    }

    @Override
    public void describeTo(Description description) {
    }

    @Factory
    static Matcher<AtlasWebElement> isCountMatch(final Integer size) {
        return new CheckElementsCountMatcher(size);
    }
}
