package nettee.draft.driving.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;

public class DraftCommandDto {
    private DraftCommandDto() {

    }

    @Builder
    public record DraftCreateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,
            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content
    ) {
    }

    @Builder
    public record DraftUpdateCommand(
            @NotNull(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,
            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content,
            @NotNull(message = "상태를 입력하십시오")
            DraftStatus status
    ) {
    }

    @Builder
    public record DraftUpdateTotalViewsCommand(
            @NotNull(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "총 조회수를 입력해주세요.")
            Integer totalViews
    ) {
    }

    @Builder
    public record DraftUpdateTotalLikesCommand(
            @NotNull(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "총 좋아요 수를 입력해주세요.")
            Integer totalLikes
    ) {
    }

    @Builder
    public record DraftUpdateTotalSharesCommand(
            @NotNull(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "총 공유수를 입력해주세요.")
            Integer totalShares
    ) {
    }

    @Builder
    public record DraftCommandResponse(
            Draft draft
    ) {

    }
}
