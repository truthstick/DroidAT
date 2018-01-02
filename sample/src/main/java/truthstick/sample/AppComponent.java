package truthstick.sample;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import truthstick.sample.authentication.AuthenticationModule;
import truthstick.sample.configuration.ConfigurationModule;
import truthstick.sample.monitoring.MonitoringModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ConfigurationModule.class,
        MonitoringModule.class,
        AuthenticationModule.class,
        SchedulersModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<SampleApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
