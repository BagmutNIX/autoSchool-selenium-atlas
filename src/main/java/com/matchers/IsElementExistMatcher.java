package com.matchers;

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