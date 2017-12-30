package truthstick.sample.splash;

import truthstick.sample.mvp.MvpPresenter;
import truthstick.sample.mvp.MvpView;

public interface SplashContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showError(String message);

        void showUpgradePrompt();

        void showNextScreen();
    }

    interface Presenter extends MvpPresenter<View> {
        void getStartedPressed();
    }
}
