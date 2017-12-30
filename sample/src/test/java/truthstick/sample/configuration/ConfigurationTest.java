package truthstick.sample.configuration;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Collections.singletonMap;

public class ConfigurationTest {

    @Test
    public void minimumVersionDefaultsToOne() throws Exception {
        Configuration testObject = new Configuration();

        assertThat(testObject.getMinimumVersion()).isEqualTo(1);
    }

    @Test
    public void minimumVersionCanBeSupplied() throws Exception {
        Configuration testObject = new Configuration(singletonMap("min.version", "42"));

        assertThat(testObject.getMinimumVersion()).isEqualTo(42);
    }
}