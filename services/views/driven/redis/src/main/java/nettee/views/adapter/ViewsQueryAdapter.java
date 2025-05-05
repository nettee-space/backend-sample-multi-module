package nettee.views.adapter;

import lombok.RequiredArgsConstructor;
import nettee.views.port.ViewsQueryRepositoryPort;
import nettee.views.repository.ViewsCountRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewsQueryAdapter implements ViewsQueryRepositoryPort {

    private final ViewsCountRepository viewsCountRepository;

    public Long getViews(Long postId) {
        return viewsCountRepository.read(postId);
    }
}
