package nettee.views.repository;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.views.Views;
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
    public boolean lock(Views views, Duration ttl) {
        String key = generateKey(views);
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, "", ttl));
    }

    /**
     * redis key 생성
     */
    private String generateKey(Views views) {
        // null 체크 필요 가능성
        Long postId = views.getPostId();
        Long userId = views.getUserId();
        String ipAddress = views.getIpAddress();
        String userAgent = views.getUserAgent();

        if (userId == null) {
            String identifier = ipAddress + userAgent;
            return KEY_FORMAT.formatted(postId, identifier);
        }
        return KEY_FORMAT.formatted(postId, userId);
    }
}
