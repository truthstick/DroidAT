package truthstick.sample.splash;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import timber.log.Timber;
import truthstick.sample.BuildConfig;
import truthstick.sample.configuration.Configuration;
import truthstick.sample.configuration.ConfigurationRepository;
import truthstick.sample.mvp.BasePresenter;

import static truthstick.sample.SchedulersModule.MAIN_THREAD;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private final ConfigurationRepository configurationRepository;
    private Scheduler mainThreadScheduler;
    private Disposable subscription = EmptyDisposable.INSTANCE;

    @Inject
    public SplashPresenter(ConfigurationRepository configurationRepository,
                           @Named(MAIN_THREAD) Scheduler mainThreadScheduler) {
        this.configurationRepository = configurationRepository;
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    public void dropView() {
        super.dropView();
        subscription.dispose();
    }

    @Override
    public void getStartedPressed() {
        subscription = configurationRepository.loadConfiguration()
                .map(this::needsUpgrade)
                .observeOn(mainThreadScheduler)
                .doOnSubscribe(__ -> view().showProgress())
                .doOnEvent((any, error) -> view().hideProgress())
                .doOnError(Timber::e)
                .subscribe(this::promptForUpgradeOrProceed, error -> view().showError(error.getMessage()));
    }

    private void promptForUpgradeOrProceed(Boolean upgradeRequired) {
        if (upgradeRequired) {
            view().showUpgradePrompt();
        } else {
            view().showNextScreen();
        }
    }

    private boolean needsUpgrade(Configuration configuration) {
        return configuration.getMinimumVersion() > BuildConfig.VERSION_CODE;
    }
}
