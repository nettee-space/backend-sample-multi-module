package nettee.views.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ViewsCountRepository {
    private final StringRedisTemplate redisTemplate;

    // post/{post_id}/view_count
    private static final String KEY_FORMAT = "post/%s/view_count";

    /**
     * 조회수 조회
     */
    public Long read(Long postId) {
        String result = redisTemplate.opsForValue().get(generateKey(postId));

        // redis 에 값이 없을 경우 0을 리턴
        return result == null ? 0L : Long.valueOf(result);
    }

    /**
     * 조회수 증가
     */
    public Long increase(Long postId) {
        return redisTemplate.opsForValue().increment(generateKey(postId));
    }

    /**
     * redis key 생성
     */
    private String generateKey(Long postId) {
        return KEY_FORMAT.formatted(postId);
    }
}
