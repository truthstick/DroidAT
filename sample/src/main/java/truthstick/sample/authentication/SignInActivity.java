package truthstick.sample.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import truthstick.sample.R;
import truthstick.sample.view.Fragments;

public class SignInActivity extends DaggerAppCompatActivity {
    @Inject
    SignInFragment injectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);

        Fragments.addIfAbsent(getSupportFragmentManager(), R.id.contentFrame, injectedFragment);
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SignInActivity.class));
    }
}

