package nettee.article.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.article.domain.type.ArticleStatus;

import java.time.Instant;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String content;
    private ArticleStatus status;
    private Integer totalViews;
    private Integer totalLikes;
    private Integer totalShares;
    private Instant createdAt;
    private Instant updatedAt;
    private Long blogId;

    @Builder(
        builderClassName = "updateArticleBuilder",
        builderMethodName = "prepareArticleUpdate",
        buildMethodName = "update"
    )
    public void update(String title, String content, Integer totalLikes, Integer totalViews, Integer totalShares) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(totalLikes, "totalLikes cannot be null");
        Objects.requireNonNull(totalLikes, "totalLikes cannot be null");
        Objects.requireNonNull(totalShares, "totalShares cannot be null");

        this.title = title;
        this.content = content;
        this.totalLikes = totalLikes;
        this.totalViews = totalViews;
        this.totalShares = totalShares;

        this.updatedAt = Instant.now();
    }
//
//    @Builder(
//            builderClassName = "totalViewsUpdateBuilder",
//            builderMethodName = "prepareTotalViewsUpdate",
//            buildMethodName = "update"
//    )
//    public void updateTotalViews(Integer totalViews) {
//        Objects.requireNonNull(totalViews, "TotalViews cannot be null");
//
//        this.totalViews = totalViews;
//        this.updatedAt = Instant.now();
//    }
//
//    @Builder(
//            builderClassName = "totalLikesUpdateBuilder",
//            builderMethodName = "prepareTotalLikesUpdate",
//            buildMethodName = "update"
//    )
//    public void updateTotalLikes(Integer totalLikes) {
//        Objects.requireNonNull(totalLikes, "TotalLikes cannot be null");
//
//        this.totalLikes = totalLikes;
//        this.updatedAt = Instant.now();
//    }
//
//    @Builder(
//            builderClassName = "totalSharesUpdateBuilder",
//            builderMethodName = "prepareTotalSharesUpdate",
//            buildMethodName = "update"
//    )
//    public void updateTotalShares(Integer totalShares) {
//        Objects.requireNonNull(totalShares, "totalShares cannot be null");
//
//        this.totalShares = totalShares;
//        this.updatedAt = Instant.now();
//    }

    public void softDelete() { this.status = ArticleStatus.DELETED; };
}
