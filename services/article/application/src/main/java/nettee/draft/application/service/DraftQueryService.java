package nettee.draft.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.readmodel.DraftQueryModels.DraftDetail;
import nettee.draft.readmodel.DraftQueryModels.DraftSummary;
import nettee.draft.application.port.DraftQueryPort;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.application.usecase.DraftReadByStatusesUseCase;
import nettee.draft.application.usecase.DraftReadUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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
