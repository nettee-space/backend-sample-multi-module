package nettee.draft.driving.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import nettee.draft.readmodel.DraftQueryModels.DraftDetail;

public class DraftQueryDto {
    private DraftQueryDto() {

    }

    @Builder
    public record DraftDetailResponse(

            @JsonProperty("draft") DraftDetail draftDetail
    ) {
    }
}
