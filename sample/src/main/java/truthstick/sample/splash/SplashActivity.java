package truthstick.sample.splash;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import truthstick.sample.R;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    SplashFragment injectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        SplashFragment splashFragment = (SplashFragment) supportFragmentManager
                .findFragmentById(R.id.contentFrame);

        if (splashFragment == null) {
            splashFragment = injectedFragment;
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentFrame, splashFragment)
                    .commit();
        }
    }
}
