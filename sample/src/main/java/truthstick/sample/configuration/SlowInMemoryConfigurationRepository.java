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
                .doOnSuccess(configuration -> doSomeLongRunningTask())
                .subscribeOn(workScheduler);
    }

    // This is ugly compared to delay(), but RxIdler doesn't detect scheduled non-blocking work.
    private void doSomeLongRunningTask() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }
}
