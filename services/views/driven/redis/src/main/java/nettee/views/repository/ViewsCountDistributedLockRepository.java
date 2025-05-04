package nettee.views.repository;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ViewsCountDistributedLockRepository {

    private final StringRedisTemplate redisTemplate;

    // post/{post_id}/user/{user_id}/lock
    private static final String KEY_FORMAT = "post/%s/user/%s/lock";

    /**
     * Distributed lock 획득
     */
    public boolean lock(Long postId, Long userId, Duration ttl) {
        String key = generateKey(postId, userId);
        return redisTemplate.opsForValue().setIfAbsent(key, "", ttl);
    }

    /**
     * redis key 생성
     */
    private String generateKey(Long postId, Long userId) {
        return KEY_FORMAT.formatted(postId, userId);
    }
}
