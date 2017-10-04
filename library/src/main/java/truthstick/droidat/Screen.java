package truthstick.droidat;

import android.app.Activity;
import android.app.Fragment;

public class Screen {
    private final Class<? extends Activity> screenUnderTest;
    private final Class<? extends Fragment> fragmentUnderTest;

    public Screen(Class<? extends Activity> screenUnderTest) {
        this(screenUnderTest, null);
    }

    public Screen(Class<? extends Activity> screenUnderTest, Class<? extends Fragment> fragmentUnderTest) {
        this.screenUnderTest = screenUnderTest;
        this.fragmentUnderTest = fragmentUnderTest;
    }
}
