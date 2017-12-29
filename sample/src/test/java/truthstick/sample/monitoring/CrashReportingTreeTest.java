package truthstick.sample.monitoring;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CrashReportingTreeTest {
    @Mock
    private CrashReporter crashReporter;
    @InjectMocks
    private CrashReportingTree testObject;

    @Test
    public void logErrorsToCrashReporter() throws Exception {
        RuntimeException exception = new RuntimeException();

        testObject.log(Log.ERROR, "Tag", "Something bad..", exception);

        then(crashReporter).should().log("Tag : Something bad..");
        then(crashReporter).should().log(exception);
    }

    @Test
    public void whenNoErrorPresentStillSendMessage() throws Exception {

        testObject.log(Log.ERROR, "Tag", "Something bad..", null);

        then(crashReporter).should().log("Tag : Something bad..");
        then(crashReporter).shouldHaveNoMoreInteractions();
    }

    @Test
    public void doesNotLogWarnings() throws Exception {

        testObject.log(Log.WARN, "Tag", "Something bad..", new RuntimeException());

        then(crashReporter).shouldHaveZeroInteractions();
    }

    @Test
    public void doesNotLogInfo() throws Exception {

        testObject.log(Log.INFO, "Tag", "Something bad..", new RuntimeException());

        then(crashReporter).shouldHaveZeroInteractions();
    }

    @Test
    public void doesNotLogDebug() throws Exception {

        testObject.log(Log.DEBUG, "Tag", "Something bad..", new RuntimeException());

        then(crashReporter).shouldHaveZeroInteractions();
    }
}