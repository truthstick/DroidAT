package truthstick.sample.view;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class Fragments {

    public static <T extends Fragment> void addIfAbsent(FragmentManager fragmentManager,
                                                        @IdRes int containerViewId,
                                                        T fragment) {
        Fragment resultFragment = fragmentManager.findFragmentById(containerViewId);

        if (resultFragment == null) {
            resultFragment = fragment;
            fragmentManager
                    .beginTransaction()
                    .add(containerViewId, resultFragment)
                    .commit();
        }
    }

    private Fragments() {
    }
}
