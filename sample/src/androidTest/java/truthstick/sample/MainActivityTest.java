package truthstick.sample;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.junit.Test;

import truthstick.droidat.BaseDroidAcceptanceTest;

import static truthstick.droidat.DroidAT.onScreen;

public class MainActivityTest extends BaseDroidAcceptanceTest  {

    @NonNull
    @Override
    protected Class<? extends Activity> startupActivity() {
        return MainActivity.class;
    }

    @Test
    public void getStartedLoadsConfiguration() throws Exception {
        onScreen(MainScreen.class)
                .selectGetStarted()
                .verifyProgressIndicatorShown();
    }
}
