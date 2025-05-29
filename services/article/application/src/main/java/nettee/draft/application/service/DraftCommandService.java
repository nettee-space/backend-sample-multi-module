package nettee.draft.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.application.port.DraftCommandPort;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.application.usecase.DraftCreateUseCase;
import nettee.draft.application.usecase.DraftDeleteUseCase;
import nettee.draft.application.usecase.DraftUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DraftCommandService implements DraftCreateUseCase, DraftUpdateUseCase, DraftDeleteUseCase {
    private final DraftCommandPort draftCommandPort;
    @Override
    public Draft createDraft(Draft draft) {
        return draftCommandPort.save(draft);
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
