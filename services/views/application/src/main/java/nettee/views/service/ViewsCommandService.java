package nettee.views.service;

import nettee.views.Views;
import nettee.views.port.ViewsCacheRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nettee.views.usecase.ViewsUpdateUseCase;

@Service
@RequiredArgsConstructor
public class ViewsCommandService implements ViewsUpdateUseCase {
    
    private final ViewsCacheRepositoryPort viewsCacheRepositoryPort;

    @Override
    public void addViewCount(Views views) {
        viewsCacheRepositoryPort.increase(views);
    }
}
