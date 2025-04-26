package nettee.views.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nettee.views.Views;
import nettee.views.port.ViewsCommandRepositoryPort;
import nettee.views.usecase.ViewsUpdateUseCase;

@Service
@RequiredArgsConstructor
public class ViewsCommandService implements ViewsUpdateUseCase {
    
    private final ViewsCommandRepositoryPort boardCommandRepositoryPort;

    @Override
    public void addViewCount(Views views) {
        boardCommandRepositoryPort.addViewCount(views);
    }
}
