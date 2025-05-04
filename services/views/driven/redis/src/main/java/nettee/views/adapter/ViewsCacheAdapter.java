package nettee.views.adapter;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.views.repository.ViewsCountBackupRepository;
import nettee.views.repository.ViewsCountDistributedLockRepository;
import nettee.views.repository.ViewsCountRepository;
import nettee.views.port.command.ViewsCacheRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
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
            viewsCountBackupRepository.updateViewCount(postId, viewCount);
        }
    }
}
