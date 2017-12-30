package truthstick.sample.authentication;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthenticationModule {

    @Provides
    public AuthenticationService providesAuthenticationService() {
        return new AuthenticationService();
    }
}
