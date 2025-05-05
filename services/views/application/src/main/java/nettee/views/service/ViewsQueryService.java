package nettee.views.service;

import lombok.RequiredArgsConstructor;
import nettee.views.port.ViewsQueryRepositoryPort;
import nettee.views.usecase.ViewsReadUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewsQueryService implements ViewsReadUseCase {
    
    private final ViewsQueryRepositoryPort viewsQueryRepositoryPort;

    @Override
    public Long getViews(Long postId) {
        return viewsQueryRepositoryPort.getViews(postId);
    }
}
