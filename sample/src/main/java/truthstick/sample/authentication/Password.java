package truthstick.sample.authentication;

import java.util.Objects;

public final class Password {
    private final String password;

    public static Password of(String password) {
        return new Password(password);
    }

    private Password(String password) {
        this.password = password;
    }

    public final boolean passwordMatches(String input) {
        return Objects.equals(input, password);
    }
}
