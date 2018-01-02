package truthstick.sample;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {
    public static final String IO = "io";
    public static final String MAIN_THREAD = "mainThread";

    @Provides
    @Named(IO)
    public Scheduler io() {
        return Schedulers.io();
    }

    @Provides
    @Named(MAIN_THREAD)
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
