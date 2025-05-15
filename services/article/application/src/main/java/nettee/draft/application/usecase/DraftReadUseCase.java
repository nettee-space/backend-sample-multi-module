package nettee.draft.application.usecase;

import nettee.draft.readmodel.DraftQueryModels.DraftDetail;
import nettee.draft.readmodel.DraftQueryModels.DraftSummary;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DraftReadUseCase {
    Optional<DraftDetail> getDraft(Long id);
    Page<DraftSummary> getAllDraft(int size);
}
