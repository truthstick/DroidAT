package truthstick.sample.configuration;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class Configuration {
    private static final Map<String, String> CONFIGURATION_DEFAULTS = ImmutableMap.of("min.version", "1");
    private final Map<String, String> configuration;

    public Configuration() {
        this(Collections.emptyMap());
    }

    public Configuration(Map<String, String> configurationValues) {
        Map<String, String> mergedConfiguration = Maps.newHashMap(CONFIGURATION_DEFAULTS);
        mergedConfiguration.putAll(configurationValues);
        this.configuration = Collections.unmodifiableMap(mergedConfiguration);
    }

    public int getMinimumVersion() {
        return Integer.valueOf(configuration.get("min.version"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configuration);
    }
}
