package nettee.draft.port;

import nettee.draft.DraftQueryModels.DraftDetail;
import nettee.draft.Draft;
import nettee.draft.type.DraftStatus;
import java.util.Optional;

public interface DraftCommandPort {
    Optional<DraftDetail> findById(Long id);
    Draft create(Draft draft);
    Draft update(Draft draft);
    void updateStatus(Long id, DraftStatus draftStatus);
}
