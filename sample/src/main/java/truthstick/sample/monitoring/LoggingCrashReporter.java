package truthstick.sample.monitoring;

import timber.log.Timber;

class LoggingCrashReporter implements CrashReporter {

    @Override
    public void log(String message) {
        Timber.i(message);
    }

    @Override
    public void log(Throwable e) {
        Timber.e(e);
    }
}
