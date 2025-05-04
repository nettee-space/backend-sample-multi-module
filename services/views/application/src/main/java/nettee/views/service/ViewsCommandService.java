package nettee.views.service;

import nettee.views.port.command.ViewsCacheRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nettee.views.usecase.ViewsUpdateUseCase;

@Service
@RequiredArgsConstructor
public class ViewsCommandService implements ViewsUpdateUseCase {
    
    private final ViewsCacheRepositoryPort viewsCacheRepositoryPort;

    @Override
    public void addViewCount(Long postId, Long userId) {
        viewsCacheRepositoryPort.increase(postId, userId);
    }
}
