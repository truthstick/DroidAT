package truthstick.sample.splash;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import timber.log.Timber;
import truthstick.sample.BuildConfig;
import truthstick.sample.configuration.Configuration;
import truthstick.sample.configuration.ConfigurationRepository;
import truthstick.sample.mvp.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private final ConfigurationRepository configurationRepository;
    private Disposable subscription = EmptyDisposable.INSTANCE;

    @Inject
    public SplashPresenter(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public void takeView(SplashContract.View view) {
        super.takeView(view);
        subscription.dispose();
    }

    @Override
    public void getStartedPressed() {
        subscription = configurationRepository.loadConfiguration()
                .map(this::needsUpgrade)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> view().showProgress())
                .doAfterTerminate(() -> view().hideProgress())
                .doOnError(Timber::e)
                .subscribe(upgradeRequired -> {
                    if (upgradeRequired) {
                        view().showUpgradePrompt();
                    } else {
                        view().showNextScreen();
                    }
                }, throwable -> view().showError(throwable.getMessage()));
    }

    private boolean needsUpgrade(Configuration configuration) {
        return configuration.getMinimumVersion() > BuildConfig.VERSION_CODE;
    }
}
