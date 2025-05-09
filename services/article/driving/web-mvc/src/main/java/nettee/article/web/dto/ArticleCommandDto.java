package nettee.article.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.article.Article;
import nettee.article.type.ArticleStatus;

public class ArticleCommandDto {
    private ArticleCommandDto() {

    }

    @Builder
    public record ArticleCreateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,
            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content,
            @NotNull(message = "상태를 입력하십시오")
            ArticleStatus status
    ) {
    }

    @Builder
    public record ArticleUpdateCommand(
            @NotBlank(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,
            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content,
            @NotNull(message = "상태를 입력하십시오")
            ArticleStatus status
    ) {
    }

    @Builder
    public record ArticleUpdateTotalViewsCommand(
            @NotBlank(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "총 조회수를 입력해주세요.")
            Integer totalViews
    ) {
    }

    @Builder
    public record ArticleUpdateTotalLikesCommand(
            @NotBlank(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "총 좋아요 수를 입력해주세요.")
            Integer totalLikes
    ) {
    }

    @Builder
    public record ArticleUpdateTotalSharesCommand(
            @NotBlank(message = "id를 입력하십시오.")
            Long id,
            @NotBlank(message = "총 공유수를 입력해주세요.")
            Integer totalShares
    ) {
    }

    @Builder
    public record ArticleCommandResponse(
            Article article
    ) {

    }
}
