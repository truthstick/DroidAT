package truthstick.sample.configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

@Module
public class ConfigurationModule {

    @Provides
    @Singleton
    public ConfigurationRepository providesConfigurationRepository() {
        return new SlowInMemoryConfigurationRepository(Schedulers.io());
    }
}
