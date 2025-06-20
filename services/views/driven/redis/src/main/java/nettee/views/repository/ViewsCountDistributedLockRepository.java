package nettee.views.repository;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.views.Views;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ViewsCountDistributedLockRepository {

    private final StringRedisTemplate redisTemplate;

    // post/{post_id}/user/{user_id}/lock
    private static final String KEY_FORMAT = "view-lock/post/%s/hash/%s";

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
        Long postId = views.getPostId();
        Long userId = views.getUserId();
        String ipAddress = views.getIpAddress();
        String userAgent = views.getUserAgent();

        String hash;
        if (userId == null) {
            String identifier = (ipAddress != null ? ipAddress : "") + (userAgent != null ? userAgent : "");
            hash = DigestUtils.sha256Hex(identifier).substring(0, 16);
        } else {
            String identifier = String.valueOf(userId);
            hash = DigestUtils.sha256Hex(identifier).substring(0, 16);
        }

        return KEY_FORMAT.formatted(postId, hash);
    }
}
