package truthstick.sample.splash;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import truthstick.sample.R;
import truthstick.sample.view.Fragments;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    SplashFragment injectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);

        Fragments.addIfAbsent(getSupportFragmentManager(), R.id.contentFrame, injectedFragment);
    }
}
