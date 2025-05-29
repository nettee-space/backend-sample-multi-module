package nettee.snowflake.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static nettee.snowflake.constants.SnowflakeConstants.NETTEE_EPOCH;
import static nettee.snowflake.constants.SnowflakeConstants.PREFIX;

@ConfigurationProperties(PREFIX)
public record SnowflakeProperties(
        Long datacenterId,
        Long workerId,
        Long epoch
) {
    private static final Logger log = LoggerFactory.getLogger(SnowflakeProperties.class);
    
    public SnowflakeProperties {
        if (datacenterId == null) {
            datacenterId = 0L;
            log.warn(PREFIX + ".datacenter-id must not be null.");
        }
        
        if (workerId == null) {
            workerId = 0L;
            log.warn(PREFIX + ".worker-id must not be null.");
        }
        
        if (epoch == null) {
            epoch = NETTEE_EPOCH;
        } else if (epoch < 0) {
            epoch = NETTEE_EPOCH;
        }
    }
}
