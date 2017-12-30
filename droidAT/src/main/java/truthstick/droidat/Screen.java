package truthstick.droidat;

import android.app.Activity;
import android.app.Fragment;

public abstract class Screen {
    private final Class<? extends Activity> expectedTopActivity;
    private final Class<? extends Fragment> expectedTopFragment;

    public Screen(Class<? extends Activity> expectedTopActivity) {
        this(expectedTopActivity, null);
    }

    public Screen(Class<? extends Activity> expectedTopActivity, Class<? extends Fragment> expectedTopFragment) {
        this.expectedTopActivity = expectedTopActivity;
        this.expectedTopFragment = expectedTopFragment;
    }

    public Class<? extends Activity> getExpectedTopActivity() {
        return expectedTopActivity;
    }

    public Class<? extends Fragment> getExpectedTopFragment() {
        return expectedTopFragment;
    }

    public boolean expectsFragment() {
        return expectedTopFragment != null;
    }
}
