package truthstick.droidat;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;

public abstract class BaseDroidAcceptanceTest {

    @Rule
    public IntentsTestRule startup = new IntentsTestRule<>(startupActivity());

    @NonNull
    abstract Class<? extends Activity> startupActivity();

}
