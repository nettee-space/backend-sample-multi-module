package nettee.redis.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Slf4j
@ConfigurationProperties("app.redis")
public record RedisProperties(
        boolean useClusterMode,
        String host,
        List<Integer> ports
) {
    public RedisProperties {
        if (!useClusterMode) {
            log.info("Redis Connection Mode is Standalone");

            if(ports.size() > 1) {
                throw new RuntimeException("Redis Connection Mode has more than one port");
            } else if(ports.isEmpty()) {
                ports.add(6379);
            }
        } else {
            log.info("Redis Connection Mode is Cluster");
        }

        if (host == null) {
            host = "127.0.0.1";
            log.warn("redis host is null");
        }

        host = host.strip();
    }
}
