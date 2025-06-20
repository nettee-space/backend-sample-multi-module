package nettee.views.adapter;

import lombok.RequiredArgsConstructor;
import nettee.views.entity.ViewsEntity;
import nettee.views.port.ViewsCommandRepositoryPort;
import nettee.views.repository.ViewsCountBackupRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewsCommandRepositoryAdapter implements ViewsCommandRepositoryPort {

    private final ViewsCountBackupRepository viewsCountBackupRepository;

    @Override
    public int updateViewCount(Long postId, Long viewCount) {
        return viewsCountBackupRepository.updateViewCount(postId, viewCount);
    }

    @Override
    public void save(Long postId, Long viewCount) {
        viewsCountBackupRepository.save(new ViewsEntity(postId, viewCount));
    }
}
