package truthstick.sample.splash;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import truthstick.sample.injection.ActivityScoped;
import truthstick.sample.injection.FragmentScoped;

@Module
public abstract class SplashModule {

    @ActivityScoped
    @Binds
    abstract SplashContract.Presenter taskPresenter(SplashPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();

}
