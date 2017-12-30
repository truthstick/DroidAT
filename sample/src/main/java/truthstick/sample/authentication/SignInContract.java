package truthstick.sample.authentication;

import truthstick.sample.mvp.MvpPresenter;
import truthstick.sample.mvp.MvpView;

public interface SignInContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void signInError();

        void signInSuccess();
    }

    interface Presenter extends MvpPresenter<View> {
        void setEmail(String user);

        void setPassword(String password);

        void signInPressed();
    }
}
