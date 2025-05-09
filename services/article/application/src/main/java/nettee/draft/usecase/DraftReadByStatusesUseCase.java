package nettee.draft.usecase;

import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.article.type.DraftStatus;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface DraftReadByStatusesUseCase {
    Page<DraftSummary> findByStatuses(Set<DraftStatus> statuses, int size);
}
