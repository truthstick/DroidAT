package truthstick.sample.authentication;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import truthstick.sample.injection.ActivityScoped;
import truthstick.sample.injection.FragmentScoped;

@Module
public abstract class SignInModule {

    @ActivityScoped
    @Binds
    abstract SignInContract.Presenter signInPresenter(SignInPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SignInFragment signInView();
}
