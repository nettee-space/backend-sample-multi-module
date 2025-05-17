package nettee.draft.driving.web.dto;

import lombok.Builder;
import nettee.draft.readmodel.DraftQueryModels.DraftDetail;

public class DraftQueryDto {
    private DraftQueryDto() {

    }

    @Builder
    public record DraftDetailResponse(
            DraftDetail draftDetail
    ) {
    }
}
