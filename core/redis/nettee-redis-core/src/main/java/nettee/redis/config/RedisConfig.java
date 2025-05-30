package nettee.redis.config;

import nettee.redis.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
        if(redisProperties.useClusterMode()) {
            // cluster connection
            throw new RuntimeException("미구현");
        } else {
            // standalone connection
            return new LettuceConnectionFactory(redisProperties.host(), redisProperties.ports().getFirst());
        }
    }
}
