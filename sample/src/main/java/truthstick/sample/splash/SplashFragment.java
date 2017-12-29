package truthstick.sample.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import truthstick.sample.R;

public class SplashFragment extends DaggerFragment implements SplashContract.View {

    @Inject SplashContract.Presenter presenter;

    @BindView(R.id.progress_bar) View progressBar;
    @BindView(R.id.get_started_button) View getStartedButton;

    @Inject
    public SplashFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getStartedButton.setOnClickListener(v -> presenter.getStartedPressed());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        getStartedButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        getStartedButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpgradePrompt() {
        showError("You should upgrade..");
    }

    @Override
    public void showNextScreen() {
        showError("Advance to next screen...");
    }
}
