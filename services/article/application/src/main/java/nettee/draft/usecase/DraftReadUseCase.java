package nettee.draft.usecase;

import nettee.draft.DraftQueryModels.DraftDetail;
import nettee.draft.DraftQueryModels.DraftSummary;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Optional;

public interface DraftReadUseCase {
    Optional<DraftDetail> getDraft(Long id);
    Page<DraftSummary> getAllDraft(int size);
}
