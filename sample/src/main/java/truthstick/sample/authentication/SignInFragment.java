package truthstick.sample.authentication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import truthstick.sample.R;
import truthstick.sample.view.AbstractTextWatcher;

public class SignInFragment extends DaggerFragment implements SignInContract.View {

    @Inject SignInContract.Presenter presenter;

    @BindView(R.id.login_progress) View progressBar;
    @BindView(R.id.sign_in_button) View signInButton;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;

    @Inject
    public SignInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_in_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        signInButton.setOnClickListener(__ -> presenter.signInPressed());

        email.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.setEmail(editable.toString());
            }
        });

        password.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.setPassword(editable.toString());
            }
        });
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
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void signInError() {
        Toast.makeText(getContext(), R.string.sign_in_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signInSuccess() {
        Toast.makeText(getContext(), R.string.sign_in_success, Toast.LENGTH_SHORT).show();
    }
}
