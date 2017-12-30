package truthstick.sample.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import truthstick.sample.R;

public class SignInActivity extends DaggerAppCompatActivity {
    @Inject
    SignInFragment injectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        SignInFragment splashFragment = (SignInFragment) supportFragmentManager
                .findFragmentById(R.id.contentFrame);

        if (splashFragment == null) {
            splashFragment = injectedFragment;
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentFrame, splashFragment)
                    .commit();
        }
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SignInActivity.class));
    }
}

