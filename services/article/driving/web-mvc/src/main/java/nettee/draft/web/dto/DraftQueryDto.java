package nettee.draft.web.dto;

import lombok.Builder;
import nettee.draft.DraftQueryModels.DraftDetail;

public class DraftQueryDto {
    private DraftQueryDto() {

    }

    @Builder
    public record DraftDetailResponse(
            DraftDetail draftDetail
    ) {
    }
}
