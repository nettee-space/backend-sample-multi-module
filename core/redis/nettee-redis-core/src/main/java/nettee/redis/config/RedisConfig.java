package nettee.redis.config;

import nettee.redis.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
        if (redisProperties.useClusterMode()) {
            // cluster connection
            List<String> clusterNodes =
                    redisProperties.ports().stream()
                            .map(port -> "%s:%d".formatted(redisProperties.host(), port))
                            .toList();
            
            return new LettuceConnectionFactory(new RedisClusterConfiguration(clusterNodes));
        } else {
            // standalone connection
            return new LettuceConnectionFactory(redisProperties.host(), redisProperties.ports().getFirst());
        }
    }
    
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        
        return redisTemplate;
    }
    
    @Bean
    StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        
        return stringRedisTemplate;
    }
    
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
