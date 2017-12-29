package truthstick.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;

import com.zombietank.sample.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import truthstick.droidat.BaseDroidAcceptanceTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static truthstick.droidat.DroidAT.onScreen;

public class MainActivityTest extends BaseDroidAcceptanceTest  {

    @NonNull
    @Override
    protected Class<? extends Activity> startupActivity() {
        return MainActivity.class;
    }

    @Test
    public void buttonIsVisible() throws Exception {
        onScreen(MainScreen.class)
                .verifyFloatingActionButtonIsVisible();
    }
}
