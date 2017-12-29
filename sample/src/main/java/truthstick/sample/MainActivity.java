package truthstick.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.zombietank.sample.R;

public class MainActivity extends AppCompatActivity {
    private Button getStartedButton;
    private ProgressBar progressBar;

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

        getStartedButton.setOnClickListener(view -> {
            getStartedButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        });
    }
}
