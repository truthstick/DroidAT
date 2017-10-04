package truthstick.droidat;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

public class BaseDroidAT {
    public static final UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    public static void pressBack() {
        device.pressBack();
    }
}
