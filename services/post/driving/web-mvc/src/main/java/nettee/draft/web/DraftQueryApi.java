package nettee.draft.web;

import lombok.RequiredArgsConstructor;
import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.draft.usecase.DraftReadByStatusesUseCase;
import nettee.draft.usecase.DraftReadUseCase;
import nettee.draft.web.dto.DraftQueryDto.DraftDetailResponse;
import nettee.draft.web.mapper.DraftDtoMapper;
import nettee.post.type.DraftStatus;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/drafts")
@RequiredArgsConstructor
public class DraftQueryApi {
    private final DraftReadUseCase draftReadUseCase;
    private final DraftReadByStatusesUseCase draftReadByStatusesUseCase;
    private final DraftDtoMapper mapper;

    @GetMapping("/{draftId}")
    public DraftDetailResponse getDraft(@PathVariable("draftId") long draftId) {
        var draft = draftReadUseCase.getDraft(draftId);
        return mapper.toDtoDetail(draft);
    }

    @GetMapping
    public Page<DraftSummary> getDraftsByStatuses(@RequestParam(defaultValue = "ACTIVE, SUSPENDED") Set<DraftStatus> statuses, Instant lastCreatedAt, int size) {
        return draftReadByStatusesUseCase.findByStatuses(statuses, size);
    }

}