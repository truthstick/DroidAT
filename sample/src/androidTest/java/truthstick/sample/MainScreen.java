package truthstick.sample;

import com.zombietank.sample.R;

import truthstick.droidat.Screen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainScreen extends Screen {

    public MainScreen() {
        super(MainActivity.class);
    }

    public MainScreen selectGetStarted() {
        onView(withId(R.id.get_started_button)).check(matches(isDisplayed())).perform(click());
        return this;
    }

    public MainScreen verifyProgressIndicatorShown() {
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
        return this;
    }
}