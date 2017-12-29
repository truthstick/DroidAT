package truthstick.sample;


import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class SampleApp extends DaggerApplication {

    @Inject
    void logInjection() {
        Log.i(SampleApp.class.getName(), "Injecting " + SampleApp.class.getSimpleName());
    }

    @Override
    protected AndroidInjector<SampleApp> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
