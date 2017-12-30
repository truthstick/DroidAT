package truthstick.sample.splash;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.junit.Test;

import truthstick.droidat.BaseDroidAcceptanceTest;

import static truthstick.droidat.DroidAT.onScreen;

public class SplashActivityTest extends BaseDroidAcceptanceTest  {

    @NonNull
    @Override
    protected Class<? extends Activity> startupActivity() {
        return SplashActivity.class;
    }

    @Test
    public void afterConfigurationLoadedProceedToSignIn() throws Exception {
        onScreen(SplashScreen.class)
                .selectGetStarted()
                .verifyProgressIndicatorShown();

        // TODO: Need to wait for io scheduler to complete to introduce this assertion.
        // onNewScreen(SignInScreen.class);
    }
}
