package truthstick.sample.monitoring;

import dagger.Module;
import dagger.Provides;

@Module
public class MonitoringModule {

    @Provides
    public CrashReporter providesCrashReporter() {
        return new LoggingCrashReporter();
    }
}
