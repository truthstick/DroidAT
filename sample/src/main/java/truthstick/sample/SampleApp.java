package truthstick.sample;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;
import truthstick.sample.monitoring.CrashReportingTree;

public class SampleApp extends DaggerApplication {

    @Inject
    CrashReportingTree crashReportingTree;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(crashReportingTree);
        }
    }

    @Override
    protected AndroidInjector<SampleApp> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
