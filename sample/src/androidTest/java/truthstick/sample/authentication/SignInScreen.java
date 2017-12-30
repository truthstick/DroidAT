package truthstick.sample.authentication;

import android.support.test.espresso.matcher.ViewMatchers;

import truthstick.droidat.Screen;
import truthstick.sample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class SignInScreen extends Screen {

    public SignInScreen() {
        super(SignInActivity.class);
    }

    public SignInScreen enterEmail(String email) {
        onView(ViewMatchers.withId(R.id.email)).check(matches(isDisplayed())).perform(typeText(email));
        return this;
    }

    public SignInScreen enterPassword(String password) {
        onView(withId(R.id.password)).check(matches(isDisplayed())).perform(typeText(password));
        return this;
    }

    public SignInScreen pressSignInButton() {
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed())).perform(click());
        return this;
    }
}
