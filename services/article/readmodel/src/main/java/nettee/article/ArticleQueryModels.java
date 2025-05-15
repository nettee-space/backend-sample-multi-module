package nettee.article;

import lombok.Builder;
import nettee.article.domain.type.ArticleStatus;

import java.time.Instant;
public final class ArticleQueryModels {
    private ArticleQueryModels() {
    }

    @Builder
    public record ArticleDetail(
            Long id,
            String title,
            String content,
            ArticleStatus status,
            Integer totalViews,
            Integer totalLikes,
            Integer totalShares,
            Instant createdAt,
            Instant updatedAt

    ) {
    }

    @Builder
    public record ArticleSummary(
            Long id,
            String title,
            ArticleStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
