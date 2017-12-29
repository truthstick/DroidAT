package truthstick.sample.configuration;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

public class SlowInMemoryConfigurationRepositoryTest {
    private TestScheduler workScheduler;
    private ConfigurationRepository testObject;

    @Before
    public void setUp() throws Exception {
        workScheduler = new TestScheduler();
        testObject = new SlowInMemoryConfigurationRepository(workScheduler);
    }

    @Test
    public void defaultConfigurationReturnedAfterDelay() throws Exception {
        TestObserver<Configuration> observer = testObject.loadConfiguration()
                .test()
                .assertNoValues()
                .assertNotComplete();

        workScheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        observer.assertValueCount(1);
    }
}