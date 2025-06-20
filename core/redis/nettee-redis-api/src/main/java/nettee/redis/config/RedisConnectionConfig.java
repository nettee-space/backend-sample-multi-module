package nettee.redis.config;

import nettee.redis.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConnectionConfig {
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
        if (redisProperties.useClusterMode()) {
            // cluster connection
            return new LettuceConnectionFactory(new RedisClusterConfiguration(redisProperties.nodes()));
        } else {
            // standalone connection
            var node = redisProperties.nodes().getFirst().split(":");
            
            return new LettuceConnectionFactory(node[0], Integer.parseInt(node[1]));
        }
    }
}
