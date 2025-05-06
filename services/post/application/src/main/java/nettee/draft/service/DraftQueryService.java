package nettee.draft.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.DraftQueryModels.DraftDetail;
import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.draft.port.DraftQueryPort;
import nettee.post.type.DraftStatus;
import nettee.draft.usecase.DraftReadByStatusesUseCase;
import nettee.draft.usecase.DraftReadUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DraftQueryService implements DraftReadUseCase, DraftReadByStatusesUseCase {
    private final DraftQueryPort draftQueryPort;
    @Override
    public Optional<DraftDetail> getDraft(Long id) {
        return draftQueryPort.findById(id);
    }
    @Override
    public Page<DraftSummary> getAllDraft(int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Direction.DESC, "createAt"));
        return draftQueryPort.findAll(pageable);
    }
    @Override
    public Page<DraftSummary> findByStatuses(Set<DraftStatus> statuses, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Direction.DESC, "createAt"));
        return draftQueryPort.findByStatuses(statuses, pageable);
    }

}
