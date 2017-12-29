package truthstick.sample;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.junit.Test;

import truthstick.droidat.BaseDroidAcceptanceTest;
import truthstick.sample.splash.SplashActivity;

import static truthstick.droidat.DroidAT.onScreen;

public class SplashActivityTest extends BaseDroidAcceptanceTest  {

    @NonNull
    @Override
    protected Class<? extends Activity> startupActivity() {
        return SplashActivity.class;
    }

    @Test
    public void getStartedLoadsConfiguration() throws Exception {
        onScreen(SplashScreen.class)
                .selectGetStarted()
                .verifyProgressIndicatorShown();
    }
}
