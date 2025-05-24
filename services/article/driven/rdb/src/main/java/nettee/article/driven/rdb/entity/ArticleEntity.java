package nettee.article.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.article.driven.rdb.entity.type.ArticleEntityStatus;
import nettee.article.driven.rdb.entity.type.ArticleEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "article")
public class ArticleEntity extends LongBaseTimeEntity {
    public String title;
    public String content;
    public Integer totalViews = 0;
    public Integer totalLikes = 0;
    public Integer totalShares = 0;

    @Convert(converter = ArticleEntityStatusConverter.class)
    public ArticleEntityStatus status;

    @Builder
    public ArticleEntity(String title, String content, ArticleEntityStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    @Builder(
            builderClassName = "updateArticleEntityBuilder",
            builderMethodName = "prepareArticleEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content, Integer totalLikes, Integer totalViews, Integer totalShares) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "status cannot be null");
        Objects.requireNonNull(totalLikes, "totalLikes cannot be null");
        Objects.requireNonNull(totalViews, "totalViews cannot be null");
        Objects.requireNonNull(totalShares, "totalShares cannot be null");

        this.title = title;
        this.content = content;
        this.totalLikes = totalLikes;
        this.totalViews = totalViews;
        this.totalShares = totalShares;
    }

    @Builder(
            builderClassName = "updateStatusArticleEntityBuilder",
            builderMethodName = "prepareArticleEntityStatusUpdate",
            buildMethodName = "updateStatus"
    )
    public void updateStatus(ArticleEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");

        this.status = status;
    }
}
