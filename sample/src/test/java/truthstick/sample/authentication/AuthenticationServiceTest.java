package truthstick.sample.authentication;

import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationServiceTest {

    private AuthenticationService testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new AuthenticationService();
    }

    @Test
    public void whenAuthenticationFailsSingleContainsAbsentUser() throws Exception {
        testObject.authenticate("not-a-real", "user")
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(Optional.absent());
    }

    @Test
    public void whenAuthenticationSucceedsUserEmitted() throws Exception {
        testObject.authenticate("tom@example.com", "qwerty123")
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(Optional.of(new User("tom", "tom@example.com")));
    }

    @Test
    public void nullUserDetailsResultInAbsentUser() throws Exception {
        testObject.authenticate(null, null)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(Optional.absent());
    }
}