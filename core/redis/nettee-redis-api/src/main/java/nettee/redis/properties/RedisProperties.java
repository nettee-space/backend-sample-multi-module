package nettee.redis.properties;

import lombok.extern.slf4j.Slf4j;
import nettee.redis.properties.cache.RedisCacheProperties;
import nettee.redis.properties.cache.domain.DomainCacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Slf4j
@ConfigurationProperties("app.redis")
public record RedisProperties(
        Boolean useClusterMode,
        List<String> nodes,
        RedisCacheProperties cache
) {
    public RedisProperties {
        if (nodes == null || nodes.isEmpty()) {
            nodes = List.of("localhost:6379");

            log.warn("redis nodes is null");
        }

        nodes = nodes.stream().map(String::strip).toList();

        if (useClusterMode == null || !useClusterMode) {
            useClusterMode = false;
            
            log.info("Redis Connection Mode is Standalone");
            
            if (nodes.size() > 1) {
                throw new RuntimeException("More than one Redis node is configured in standalone mode");
            }
        } else {
            log.info("Redis Connection Mode is Cluster");
        }
        
        if (cache == null) {
            cache = new RedisCacheProperties(
                    Map.of("app", new DomainCacheProperties(null, null, null))
            );
            
            log.warn("Redis cache is null");
        }
    }
}
