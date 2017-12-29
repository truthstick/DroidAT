package truthstick.sample.configuration;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;

import java.util.Collections;
import java.util.Map;

public class Configuration {
    private static final Map<String, String> CONFIGURATION_DEFAULTS = ImmutableMap.of("min.version", "1");
    private final Map<String, String> configuration;

    public Configuration() {
        this(Collections.emptyMap());
    }

    public Configuration(Map<String, String> configurationValues) {
        this.configuration = ImmutableMap.<String, String>builder()
                .putAll(CONFIGURATION_DEFAULTS)
                .putAll(configurationValues)
                .build();
    }

    public int getMinimumVersion() {
        return Ints.tryParse(configuration.get("min.version"), 0);
    }
}
