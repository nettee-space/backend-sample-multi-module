package nettee.snowflake.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

import static nettee.snowflake.constants.SnowflakeConstants.NETTEE_EPOCH;
import static nettee.snowflake.constants.SnowflakeConstants.PREFIX;

@ConfigurationProperties(PREFIX)
public record SnowflakeProperties(
        Long datacenterId,
        Long workerId,
        Long epoch
) {
    public SnowflakeProperties {
        Objects.requireNonNull(datacenterId, PREFIX + ".datacenter-id must not be null.");
        Objects.requireNonNull(workerId, PREFIX + ".worker-id must not be null.");
        
        if (epoch == null) {
            epoch = NETTEE_EPOCH;
        } else if (epoch < 0) {
            epoch = NETTEE_EPOCH;
        }
    }
}
