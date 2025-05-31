package nettee.redis.config;

import nettee.redis.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
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
}
