package truthstick.sample.configuration;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;

class SlowInMemoryConfigurationRepository implements ConfigurationRepository {
    private final Scheduler workScheduler;

    public SlowInMemoryConfigurationRepository(Scheduler workScheduler) {
        this.workScheduler = workScheduler;
    }

    @Override
    public Single<Configuration> loadConfiguration() {
        return Single.just(new Configuration())
                .delay(3, TimeUnit.SECONDS, workScheduler);

    }
}
