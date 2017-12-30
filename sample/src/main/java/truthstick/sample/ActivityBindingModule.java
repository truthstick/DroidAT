package truthstick.sample;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import truthstick.sample.authentication.SignInActivity;
import truthstick.sample.authentication.SignInModule;
import truthstick.sample.injection.ActivityScoped;
import truthstick.sample.splash.SplashActivity;
import truthstick.sample.splash.SplashModule;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity splashActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract SignInActivity signInActivity();
}
