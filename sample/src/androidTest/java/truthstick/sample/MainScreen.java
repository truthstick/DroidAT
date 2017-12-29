package truthstick.sample;

import com.zombietank.sample.R;

import truthstick.droidat.Screen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainScreen extends Screen {

    public MainScreen() {
        super(MainActivity.class);
    }

    public void verifyFloatingActionButtonIsVisible() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }
}
