package truthstick.sample.monitoring;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import timber.log.Timber;

public class CrashReportingTree extends Timber.Tree {
    private final CrashReporter crashReporter;

    @Inject
    public CrashReportingTree(CrashReporter crashReporter) {
        this.crashReporter = crashReporter;
    }

    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable t) {
        if (priority == Log.ERROR) {
            crashReporter.log(tag + " : " + message);
            if (t != null) {
                crashReporter.log(t);
            }
        }
    }
}
