package nettee.views.service;

import java.time.Duration;
import nettee.views.Views;
import nettee.views.port.ViewsCommandRepositoryPort;
import nettee.views.port.ViewsCacheRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nettee.views.usecase.ViewsUpdateUseCase;

@Service
@RequiredArgsConstructor
public class ViewsCommandService implements ViewsUpdateUseCase {
    
    private final ViewsCacheRepositoryPort viewsCacheRepositoryPort;
    private final ViewsCommandRepositoryPort viewsRdbPort;

    private static final int BACK_UP_BACH_SIZE = 100;
    private static final Duration TTL = Duration.ofMinutes(10);

    @Override
    public void addViewCount(Views views) {
        // Distributed Lock 획득 실패 시, increase 하지 않음
        if (!viewsCacheRepositoryPort.getLock(views, TTL)) {
            return;
        }

        // Redis 조회수 증가
        Long postId = views.getPostId();
        Long viewCount = viewsCacheRepositoryPort.increase(postId);

        // BATCH_SIZE 시, RDB 저장
        if (viewCount % BACK_UP_BACH_SIZE == 0) {
            int result = viewsRdbPort.updateViewCount(postId, viewCount);

            // DB에 값이 없을 경우, INSERT
            // 게시글 생성 시 조회수 0으로 초기화 할 수도 있음
            if (result == 0) {
                viewsRdbPort.save(postId, viewCount);
            }
        }

    }
}
