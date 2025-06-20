package nettee.draft.driving.web;

import lombok.RequiredArgsConstructor;
import nettee.draft.driving.web.dto.DraftQueryDto.DraftDetailResponse;
import nettee.draft.readmodel.DraftQueryModels.DraftSummary;
import nettee.draft.application.usecase.DraftReadByStatusesUseCase;
import nettee.draft.application.usecase.DraftReadUseCase;
import nettee.draft.readmodel.DraftQueryModels;
import nettee.draft.domain.type.DraftStatus;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import static nettee.draft.exception.DraftQueryErrorCode.DRAFT_NOT_FOUND;

@RestController
@RequestMapping("drafts")
@RequiredArgsConstructor
public class DraftQueryApi {
    private final DraftReadUseCase draftReadUseCase;
    private final DraftReadByStatusesUseCase draftReadByStatusesUseCase;
//    private final DraftDtoMapper mapper;

    @GetMapping("/{draftId}")
    public DraftDetailResponse getDraft(@PathVariable("draftId") long draftId) {
        DraftQueryModels.DraftDetail draftDetail = draftReadUseCase.getDraft(draftId)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return new DraftDetailResponse(draftDetail);
    }

    @GetMapping
    public Page<DraftSummary> getDraftsByStatuses(
            @RequestParam(defaultValue = "PENDING, SUSPENDED") Set<DraftStatus> statuses,
            @RequestParam(defaultValue = "100") int size) {
        return draftReadByStatusesUseCase.findByStatuses(statuses, size);
    }
}