package nettee.draft.port;

import nettee.draft.DraftQueryModels.DraftDetail;
import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.article.type.DraftStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface DraftQueryPort {
    Optional<DraftDetail> findById(Long id);
    Page<DraftSummary> findAll(Pageable pageable);

    Page<DraftSummary> findByStatuses(Set<DraftStatus> statuses, Pageable pageable);

}
