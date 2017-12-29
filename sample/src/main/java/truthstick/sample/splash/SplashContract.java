package truthstick.sample.splash;

import truthstick.sample.BasePresenter;
import truthstick.sample.BaseView;

public interface SplashContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showError(String message);

        void showUpgradePrompt();

        void showNextScreen();
    }

    interface Presenter extends BasePresenter<View> {
        void getStartedPressed();
    }
}
