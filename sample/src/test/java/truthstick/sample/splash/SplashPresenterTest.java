package truthstick.sample.splash;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;
import truthstick.sample.configuration.Configuration;
import truthstick.sample.configuration.ConfigurationRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {
    @Mock private ConfigurationRepository configurationRepository;
    @Mock private SplashContract.View view;

    @InjectMocks
    private SplashPresenter testObject;
    private TestScheduler mainThreadScheduler;

    @Before
    public void setUp() throws Exception {
        mainThreadScheduler = new TestScheduler();
        this.testObject = new SplashPresenter(configurationRepository, mainThreadScheduler);
    }

    @Test
    public void getStartedLoadsConfiguration() throws Exception {
        InOrder inOrder = inOrder(view);
        given(configurationRepository.loadConfiguration()).willReturn(Single.just(new Configuration()));
        testObject.takeView(view);
        testObject.getStartedPressed();

        then(view).should(inOrder).showProgress();

        mainThreadScheduler.triggerActions();

        then(view).should(inOrder).hideProgress();
        then(view).should(inOrder).showNextScreen();
    }

    @Test
    public void whenVersionIsTooOldPromptForUpgrade() throws Exception {
        InOrder inOrder = inOrder(view);
        Configuration item = new Configuration(Collections.singletonMap("min.version", String.valueOf(Integer.MAX_VALUE)));
        given(configurationRepository.loadConfiguration()).willReturn(Single.just(item));
        testObject.takeView(view);
        testObject.getStartedPressed();

        then(view).should(inOrder).showProgress();

        mainThreadScheduler.triggerActions();

        then(view).should(inOrder).hideProgress();
        then(view).should(inOrder).showUpgradePrompt();
    }

    @Test
    public void whenErrorOccursLoadingConfigShowToUser() throws Exception {
        InOrder inOrder = inOrder(view);
        Throwable error = new RuntimeException("It did not work");
        given(configurationRepository.loadConfiguration()).willReturn(Single.error(error));
        testObject.takeView(view);
        testObject.getStartedPressed();

        then(view).should(inOrder).showProgress();

        mainThreadScheduler.triggerActions();

        then(view).should(inOrder).hideProgress();
        then(view).should(inOrder).showError("It did not work");
        then(view).shouldHaveNoMoreInteractions();
    }

    @Test
    public void whenViewDroppedWhileInProgressDisposeSubscription() throws Exception {
        InOrder inOrder = inOrder(view);
        given(configurationRepository.loadConfiguration()).willReturn(Single.just(new Configuration()));
        testObject.takeView(view);
        testObject.getStartedPressed();
        then(view).should(inOrder).showProgress();

        testObject.dropView();

        mainThreadScheduler.triggerActions();

        then(view).shouldHaveNoMoreInteractions();
    }
}
