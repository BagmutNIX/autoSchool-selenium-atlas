package matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class IsElementDisplayedMatcher extends TypeSafeMatcher<WebElement> {
    private int timeout = 10;

    public IsElementDisplayedMatcher() {
    }

    public IsElementDisplayedMatcher(int timeout) {
        this.timeout = timeout;
    }

    @Override
    protected boolean matchesSafely(WebElement item) {
        long searchTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeout);
        boolean isDisplayed = false;
        while (System.currentTimeMillis() <= searchTime && !isDisplayed) {
            try {
                Thread.sleep(200);
                isDisplayed = item.isDisplayed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isDisplayed;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is displayed on page");
    }

    @Factory
    public static Matcher<WebElement> isDisplayed() {
        return new IsElementDisplayedMatcher();
    }

    @Factory
    public static Matcher<WebElement> isDisplayed(int timeout) {
        return new IsElementDisplayedMatcher(timeout);
    }
}
