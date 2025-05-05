package nettee.views.adapter;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.views.entity.ViewsEntity;
import nettee.views.port.ViewsCacheRepositoryPort;
import nettee.views.repository.ViewsCountBackupRepository;
import nettee.views.repository.ViewsCountDistributedLockRepository;
import nettee.views.repository.ViewsCountRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewsCacheAdapter implements ViewsCacheRepositoryPort {

    private final ViewsCountRepository viewsCountRepository;
    private final ViewsCountBackupRepository viewsCountBackupRepository;
    private final ViewsCountDistributedLockRepository viewsCountDistributedLockRepository;

    private static final int BACK_UP_BACH_SIZE = 100;
    private static final Duration TTL = Duration.ofMinutes(10);

    @Override
    public void increase(Long postId, Long userId) {
        // Distributed Lock 획득 실패 시, increase 하지 않음
        if (!viewsCountDistributedLockRepository.lock(postId, userId, TTL)) {
            return;
        }

        // BATCH_SIZE 시, RDB 저장
        Long viewCount = viewsCountRepository.increase(postId);
        if (viewCount % BACK_UP_BACH_SIZE == 0) {
            int result = viewsCountBackupRepository.updateViewCount(postId, viewCount);

            // DB에 값이 없을 경우, INSERT
            // 게시글 생성 시 조회수 0으로 초기화 할 수도 있음
            if (result == 0) {
                viewsCountBackupRepository.save(new ViewsEntity(postId, viewCount));
            }
        }
    }
}
