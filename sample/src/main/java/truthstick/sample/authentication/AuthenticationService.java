package truthstick.sample.authentication;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AuthenticationService {
    private static final Map<User, Password> USERS = ImmutableMap.of(
            new User("tom", "tom@example.com"), Password.of("qwerty123"),
            new User("bob", "bob@example.com"), Password.of("hey bobbay!")
    );

    public Single<Optional<User>> authenticate(String email, String password) {
        return Observable.fromIterable(USERS.entrySet())
                .filter(userPasswordEntry -> userPasswordEntry.getKey().emailMatches(email))
                .filter(userPasswordEntry -> userPasswordEntry.getValue().passwordMatches(password))
                .firstElement()
                .map(Map.Entry::getKey)
                .map(Optional::of)
                .defaultIfEmpty(Optional.absent())
                .toSingle();
    }
}
