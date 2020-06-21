package matchers;

import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class HasTextMatcher extends TypeSafeMatcher<WebElement> {
    private int timeout = 10;
    private String textMatcher;

    public HasTextMatcher(String textMatcher) {
        this.textMatcher = textMatcher;
    }

    public HasTextMatcher(String textMatcher, int timeout) {
        this.textMatcher = textMatcher;
        this.timeout = timeout;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        long waitUntil = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeout);
        boolean matched = false;
        while (System.currentTimeMillis() <= waitUntil && !matched) {
            try {
                matched = item.getText().contains(textMatcher);
                Thread.sleep(250);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return matched;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element text ").appendText(textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("text of element ").appendValue(item).appendText(" was ")
                .appendValue(item.getText()).appendText(" while waiting ").appendValue(timeout).appendText(" seconds.");
    }

    @Factory
    public static Matcher<WebElement> hasText(final String textMatcher) {
        return new HasTextMatcher(textMatcher);
    }

    @Factory
    public static Matcher<WebElement> hasText(final String textMatcher, int timeout) {
        return new HasTextMatcher(textMatcher, timeout);
    }

}
