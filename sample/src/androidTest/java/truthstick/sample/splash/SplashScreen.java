package truthstick.sample.splash;

import truthstick.droidat.Screen;
import truthstick.sample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class SplashScreen extends Screen {

    public SplashScreen() {
        super(SplashActivity.class);
    }

    public SplashScreen selectGetStarted() {
        onView(withId(R.id.get_started_button)).check(matches(isDisplayed())).perform(click());
        return this;
    }
}