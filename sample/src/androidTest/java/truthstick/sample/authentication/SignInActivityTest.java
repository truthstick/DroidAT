package truthstick.sample.authentication;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.junit.Test;

import truthstick.droidat.BaseDroidAcceptanceTest;

import static truthstick.droidat.DroidAT.onScreen;

public class SignInActivityTest extends BaseDroidAcceptanceTest {

    @NonNull
    @Override
    protected Class<? extends Activity> startupActivity() {
        return SignInActivity.class;
    }

    @Test
    public void signInFailure() throws Exception {
        onScreen(SignInScreen.class)
                .enterEmail("bad@example.com")
                .enterPassword("scrambled eggs")
                .pressSignInButton();
    }
}
