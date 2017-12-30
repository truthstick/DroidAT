package truthstick.sample.configuration;

import io.reactivex.Single;

public interface ConfigurationRepository {
    Single<Configuration> loadConfiguration();
}
