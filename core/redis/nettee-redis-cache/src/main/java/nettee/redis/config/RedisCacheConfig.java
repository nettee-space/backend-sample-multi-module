package nettee.redis.config;

import nettee.redis.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisCacheConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory, RedisProperties redisProperties) {
        // 도메인 별 캐시 적용
        Map<String, RedisCacheConfiguration> domainCacheConfigurations = redisProperties.cache().domains().entrySet().stream()
                .collect(Collectors.toMap(
                        // 도메인명 (ex: article)
                        Map.Entry::getKey,
                        entry -> {
                            var domainCacheProperties = entry.getValue();
                            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                    .entryTtl(Duration.ofSeconds(domainCacheProperties.ttl()));
                            
                            if (domainCacheProperties.disableNull()) {
                                config = config.disableCachingNullValues();
                            }
                            
                            config = config.computePrefixWith(cacheName -> domainCacheProperties.prefix());
                            
                            return config;
                        }
                ));
        
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .transactionAware()
                .withInitialCacheConfigurations(domainCacheConfigurations)
                .build();
    }
}
