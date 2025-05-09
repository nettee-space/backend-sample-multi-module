package nettee.draft.service;

import lombok.RequiredArgsConstructor;
import nettee.article.Draft;
import nettee.draft.port.DraftCommandPort;
import nettee.article.type.DraftStatus;
import nettee.draft.usecase.DraftCreateUseCase;
import nettee.draft.usecase.DraftDeleteUseCase;
import nettee.draft.usecase.DraftUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DraftCommandService implements DraftCreateUseCase, DraftUpdateUseCase, DraftDeleteUseCase {
    private final DraftCommandPort draftCommandPort;
    @Override
    public Draft createDraft(Draft draft) {
        return draftCommandPort.create(draft);
    }
    @Override
    public Draft updateDraft(Draft draft) {
        return draftCommandPort.update(draft);
    }

    @Override
    public void deleteDraft(Long id) {
        draftCommandPort.updateStatus(id, DraftStatus.DELETED);
    }
}
