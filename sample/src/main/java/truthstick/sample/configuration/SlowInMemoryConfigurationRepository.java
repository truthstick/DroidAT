package truthstick.sample.configuration;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

class SlowInMemoryConfigurationRepository implements ConfigurationRepository {

    @Override
    public Single<Configuration> loadConfiguration() {
        return Single.just(new Configuration())
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
    }
}
