package nettee.views.adapter;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.views.Views;
import nettee.views.port.ViewsCacheRepositoryPort;
import nettee.views.repository.ViewsCountDistributedLockRepository;
import nettee.views.repository.ViewsCountRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewsCacheAdapter implements ViewsCacheRepositoryPort {

    private final ViewsCountRepository viewsCountRepository;
    private final ViewsCountDistributedLockRepository viewsCountDistributedLockRepository;


    @Override
    public boolean getLock(Views views, Duration ttl) {
        return viewsCountDistributedLockRepository.lock(views, ttl);
    }

    @Override
    public Long increase(Long postId) {
        return viewsCountRepository.increase(postId);
    }
}
