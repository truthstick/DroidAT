package truthstick.sample.configuration;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

import static truthstick.sample.SchedulersModule.IO;

@Module
public class ConfigurationModule {

    @Provides
    @Singleton
    public ConfigurationRepository providesConfigurationRepository(@Named(IO) Scheduler io) {
        return new SlowInMemoryConfigurationRepository(io);
    }
}
