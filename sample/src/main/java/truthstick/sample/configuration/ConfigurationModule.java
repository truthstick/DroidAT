package truthstick.sample.configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigurationModule {

    @Provides
    @Singleton
    public ConfigurationRepository providesConfigurationRepository() {
        return new SlowInMemoryConfigurationRepository();
    }
}
