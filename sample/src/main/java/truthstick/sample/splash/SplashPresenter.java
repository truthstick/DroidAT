package truthstick.sample.splash;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;
import truthstick.sample.BuildConfig;
import truthstick.sample.configuration.Configuration;
import truthstick.sample.configuration.ConfigurationRepository;

public class SplashPresenter implements SplashContract.Presenter {
    private final ConfigurationRepository configurationRepository;
    private SplashContract.View view;
    private Disposable subscription;

    @Inject
    public SplashPresenter(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public void takeView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        if (subscription != null) {
            subscription.dispose();
            subscription = null;
        }
        this.view = null;
    }

    @Override
    public void getStartedPressed() {
        subscription = configurationRepository.loadConfiguration()
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::needsUpgrade)
                .doOnSubscribe(disposable -> view.showProgress())
                .doAfterTerminate(() -> view.hideProgress())
                .subscribe(upgradeRequired -> {
                    if (upgradeRequired) {
                        view.showUpgradePrompt();
                    } else {
                        view.showNextScreen();
                    }
                }, throwable -> {
                    Timber.e(throwable);
                    view.showError(throwable.getMessage());
                });

    }

    private boolean needsUpgrade(Configuration configuration) {
        return configuration.getMinimumVersion() > BuildConfig.VERSION_CODE;
    }
}
