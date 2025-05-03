package nettee.draft.usecase;

import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.post.PostQueryModels.PostSummary;
import nettee.post.type.DraftStatus;
import nettee.post.type.PostStatus;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Set;

public interface DraftReadByStatusesUseCase {
    Page<DraftSummary> findByStatuses(Set<DraftStatus> statuses, int size);
}
