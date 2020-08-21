package com.matchers;

import org.assertj.core.internal.bytebuddy.matcher.StringMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CheckStringMatcher extends TypeSafeMatcher<String> {

    private final String expectedString;

    public CheckStringMatcher(final String expectedString) {
        this.expectedString = expectedString;
    }

    @Override
    protected boolean matchesSafely(String s) {
        return false;
    }

    @Override
    public void describeTo(Description description) {
    }

    public static CheckStringMatcher matchesStr(final String expectedString) {
        return new CheckStringMatcher(expectedString);
    }

}
