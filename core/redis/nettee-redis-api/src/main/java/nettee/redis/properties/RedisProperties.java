package nettee.redis.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@ConfigurationProperties("app.redis")
public record RedisProperties(
        String host,
        Integer port
) {
    public RedisProperties {
        
        if (host == null) {
            host = "127.0.0.1";
            log.warn("redis host is null");
        }
        
        host = host.strip();
        
        if (port == null) {
            port = 6379;
            log.warn("redis port is null");
        }
    }
}
