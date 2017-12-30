package truthstick.sample.authentication;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import truthstick.sample.mvp.BasePresenter;

public class SignInPresenter extends BasePresenter<SignInContract.View> implements SignInContract.Presenter {
    private final AuthenticationService authenticationService;
    private String email;
    private String password;
    private Disposable subscription = EmptyDisposable.INSTANCE;

    @Inject
    public SignInPresenter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void dropView() {
        super.dropView();
        subscription.dispose();
    }

    @Override
    public void signInPressed() {
        subscription = authenticationService.authenticate(email, password)
                .doOnSubscribe(__ -> view().showProgress())
                .doAfterTerminate(() -> view().hideProgress())
                .subscribe(userOptional -> {
                    if (userOptional.isPresent()) {
                        view().signInSuccess();
                    } else {
                        view().signInError();
                    }
                });
    }
}
