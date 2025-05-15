package nettee.draft.application.usecase;

import nettee.draft.readmodel.DraftQueryModels.DraftSummary;
import nettee.draft.domain.type.DraftStatus;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface DraftReadByStatusesUseCase {
    Page<DraftSummary> findByStatuses(Set<DraftStatus> statuses, int size);
}
