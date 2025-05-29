package nettee.draft.application.port;

import nettee.draft.readmodel.DraftQueryModels.DraftDetail;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;
import java.util.Optional;

public interface DraftCommandPort {
    Optional<DraftDetail> findById(Long id);
    Draft save(Draft draft);
    Draft update(Draft draft);
    void updateStatus(Long id, DraftStatus draftStatus);
}
