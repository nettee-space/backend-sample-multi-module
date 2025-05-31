package nettee.redis.properties;

import lombok.extern.slf4j.Slf4j;
import nettee.redis.properties.cache.RedisCacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Slf4j
@ConfigurationProperties("app.redis")
public record RedisProperties(
        Boolean useClusterMode,
        String host,
        List<Integer> ports,
        RedisCacheProperties cache
) {
    public RedisProperties {
        if(ports == null || ports.isEmpty()) {
            ports = List.of(6379);
        }
        
        if (useClusterMode == null || !useClusterMode) {
            useClusterMode = false;
            
            log.info("Redis Connection Mode is Standalone");

            if(ports.size() > 1) {
                throw new RuntimeException("Redis Connection Mode has more than one port");
            }
        } else {
            log.info("Redis Connection Mode is Cluster");
        }

        if (host == null || host.isBlank()) {
            host = "127.0.0.1";
            log.warn("redis host is null");
        }

        host = host.strip();
    }
}
