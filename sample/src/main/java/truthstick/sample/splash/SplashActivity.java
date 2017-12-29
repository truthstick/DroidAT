package truthstick.sample.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import truthstick.sample.R;
import truthstick.sample.configuration.ConfigurationRepository;

public class SplashActivity extends DaggerAppCompatActivity {
    private Button getStartedButton;
    private ProgressBar progressBar;

    @Inject ConfigurationRepository configurationRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStartedButton = findViewById(R.id.get_started_button);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Log.d("Splash", "configRepo: " + configurationRepository);

        getStartedButton.setOnClickListener(view -> {
            getStartedButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        });
    }
}
