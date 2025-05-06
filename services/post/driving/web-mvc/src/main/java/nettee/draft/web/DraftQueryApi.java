package nettee.draft.web;

import lombok.RequiredArgsConstructor;
import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.draft.usecase.DraftReadByStatusesUseCase;
import nettee.draft.usecase.DraftReadUseCase;
import nettee.draft.web.dto.DraftQueryDto.DraftDetailResponse;
import nettee.draft.web.mapper.DraftDtoMapper;
import nettee.draft.DraftQueryModels;
import nettee.post.type.DraftStatus;
import nettee.draft.web.dto.DraftQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import static nettee.draft.DraftQueryErrorCode.DRAFT_NOT_FOUND;

@RestController
@RequestMapping("/api/v1/drafts")
@RequiredArgsConstructor
public class DraftQueryApi {
    private final DraftReadUseCase draftReadUseCase;
    private final DraftReadByStatusesUseCase draftReadByStatusesUseCase;
//    private final DraftDtoMapper mapper;

    @GetMapping("/{draftId}")
    public DraftDetailResponse getDraft(@PathVariable("draftId") long draftId) {
        DraftQueryModels.DraftDetail draftDetail = draftReadUseCase.getDraft(draftId)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return new DraftQueryDto.DraftDetailResponse(draftDetail);
    }

    @GetMapping
    public Page<DraftSummary> getDraftsByStatuses(
            @RequestParam(defaultValue = "ACTIVE, SUSPENDED") Set<DraftStatus> statuses,
            @RequestParam(defaultValue = "100") int size) {
        return draftReadByStatusesUseCase.findByStatuses(statuses, size);
    }
}