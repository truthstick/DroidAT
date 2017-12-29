package truthstick.sample.monitoring;

public interface CrashReporter {
    void log(String message);
    void log(Throwable e);
}
