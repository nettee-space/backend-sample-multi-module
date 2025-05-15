package nettee.draft.application.port;

import nettee.draft.readmodel.DraftQueryModels.DraftDetail;
import nettee.draft.readmodel.DraftQueryModels.DraftSummary;
import nettee.draft.domain.type.DraftStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface DraftQueryPort {
    Optional<DraftDetail> findById(Long id);
    Page<DraftSummary> findAll(Pageable pageable);

    Page<DraftSummary> findByStatuses(Set<DraftStatus> statuses, Pageable pageable);

}
