package truthstick.sample.splash;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.junit.Test;

import truthstick.droidat.BaseDroidAcceptanceTest;
import truthstick.sample.authentication.SignInScreen;

import static truthstick.droidat.DroidAT.onNewScreen;
import static truthstick.droidat.DroidAT.onScreen;

public class SplashActivityTest extends BaseDroidAcceptanceTest {

    @NonNull
    @Override
    protected Class<? extends Activity> startupActivity() {
        return SplashActivity.class;
    }

    @Test
    public void afterConfigurationLoadedProceedToSignIn() throws Exception {
        onScreen(SplashScreen.class)
                .selectGetStarted();

        onNewScreen(SignInScreen.class);
    }
}
