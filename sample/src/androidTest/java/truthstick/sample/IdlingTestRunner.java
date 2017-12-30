package truthstick.sample;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.squareup.rx2.idler.Rx2Idler;

import io.reactivex.plugins.RxJavaPlugins;

public class IdlingTestRunner extends AndroidJUnitRunner {
    @Override
    public void onCreate(Bundle arguments) {
        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("Computation Scheduler"));
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("IO Scheduler"));
        RxJavaPlugins.setInitNewThreadSchedulerHandler(Rx2Idler.create("New thread Scheduler"));
        RxJavaPlugins.setInitSingleSchedulerHandler(Rx2Idler.create("Single scheduler"));
        super.onCreate(arguments);
    }
}
