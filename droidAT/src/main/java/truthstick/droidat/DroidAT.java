package truthstick.droidat;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.test.uiautomator.SearchCondition;
import android.support.test.uiautomator.UiDevice;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DroidAT {
    private static final String TAG = "DroidAT";

    public static final UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    private static <T extends Screen> T instantiateScreen(Class<T> screen) {
        try {
            return screen.getConstructor().newInstance();
        } catch (Exception e) {
            throw new AssertionError("Unable to create screen: " + screen.getClass().getName(), e);
        }
    }

    public static <T extends Screen> T onNewScreen(Class<T> uatScreen) {
        T screen = null;

        try {
            screen = instantiateScreen(uatScreen);
            Intents.intended(IntentMatchers.hasComponent(new ComponentName(InstrumentationRegistry.getTargetContext(), screen.getExpectedTopActivity())));
            resetIntentTracking();
        } catch (AssertionFailedError e) {
            fail("Activity was not started correctly by intent: " + e.getMessage());
        }

        return screen;
    }

    public static <T extends Screen> T onScreen(Class<T> uatScreen) {
        // Returning with result does not show up in intents so we are just instantiating

        sleep(500, TimeUnit.MILLISECONDS);
        T screen = instantiateScreen(uatScreen);

        resetIntentTracking();

        if (screen.expectsFragment()) {
            Class targetFragment = screen.getExpectedTopFragment();

            Class<? extends Fragment> topFragment = findTopFragment((FragmentActivity) getActivityInstance());

            if (topFragment == null) {
                fail("No fragments available");
            }

            assertTrue("Expected fragment [" + targetFragment.getSimpleName()
                            + "] to be on top but was [" + topFragment.getSimpleName() + "]",
                    targetFragment.isAssignableFrom(topFragment));
        }

        return screen;
    }

    private static Class<? extends Fragment> findTopFragment(FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        List<android.support.v4.app.Fragment> fragments = fragmentManager.getFragments();
        int fragmentCount = fragments.size();

        if (fragmentCount == 0) {
            return null;
        }

        return fragments.get(fragmentCount - 1).getClass();
    }

    public static <T extends Screen> T pressBackUntilOnScreen(Class<T> uatScreen) {
        T screen = instantiateScreen(uatScreen);

        resetIntentTracking();

        String startingPackageName = InstrumentationRegistry.getTargetContext().getPackageName();

        boolean onWrongScreen = true;

        do {
            pressBack();

            Activity topActivity = getActivityInstance();

            if (topActivity == null || !startingPackageName.equals(topActivity.getPackageName())) {
                fail("Target activity not found in backstack.");
            } else {
                if (screen.getExpectedTopActivity().isAssignableFrom(topActivity.getClass())) {
                    if (!screen.expectsFragment()) {
                        onWrongScreen = false;
                    } else {
                        Class targetFragment = screen.getExpectedTopFragment();

                        Class<? extends Fragment> topFragment = findTopFragment((FragmentActivity) getActivityInstance());

                        if (topFragment != null && targetFragment.isAssignableFrom(topFragment)) {
                            onWrongScreen = false;
                        }
                    }
                }
            }
        } while (onWrongScreen);

        return screen;
    }

    public static void sleep(int duration, TimeUnit unit) {
        sleepQuietly(duration, unit);
    }

    public static void sleepQuietly(long duration, TimeUnit unit) {
        try {
            unit.sleep(duration);
        } catch (InterruptedException e) {
            Log.d(TAG, "Sleep interrupted", e);
        }
    }

    public static Activity getActivityInstance() {
        final AtomicReference<Activity> activityReference = new AtomicReference<>();
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (!resumedActivities.isEmpty()) {
                    activityReference.set(resumedActivities.iterator().next());
                }
            }
        });
        return activityReference.get();
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                if (v != null) {
                    boolean clickPerformed = v.performClick();
                    if (!clickPerformed) {
                        throw new AssertionError("Failed to click on view with id: " + id);
                    }
                }
            }
        };
    }

    public static <T> T waitForObjectWithAPI19Fix(SearchCondition<T> condition, int timeout) {
        T wait = null;

        try {
            wait = device.wait(condition, timeout);
        } catch (NullPointerException nullPointerExceptionFromUIAutomatorInternals) {
            int sdkInt = Build.VERSION.SDK_INT;
            if (sdkInt < Build.VERSION_CODES.LOLLIPOP) {
                sleep(1, TimeUnit.SECONDS); // IF < LOLLIPOP, there is a known bug with UIAutomator throwing internal NPE, let the system pause and try again

                try {
                    wait = device.wait(condition, timeout);
                } catch (NullPointerException npe) {
                    fail("NPE generated under API 19, unable to find object, likely an UIAutomator bug.");
                }
            } else {
                throw nullPointerExceptionFromUIAutomatorInternals;
            }
        }
        return wait;
    }

    public static String getStringFromResourceID(@StringRes int id, Object... args) {
        return InstrumentationRegistry.getTargetContext().getString(id, args);
    }

    public static String getPluralFromResourceID(@PluralsRes int id, int quantity, Object... args) {
        return InstrumentationRegistry.getTargetContext().getResources().getQuantityString(id, quantity, args);
    }

    public static void pressBack() {
        device.pressBack();
    }

    public ViewAction loopMainThreadForAtLeast(final int milliseconds) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "sleep for " + milliseconds + "ms";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(milliseconds);
                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    private static void resetIntentTracking() {
        Intents.release();
        Intents.init();
    }
}
