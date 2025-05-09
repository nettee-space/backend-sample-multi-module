package nettee.article.usecase;

import nettee.article.ArticleQueryModels.ArticleSummary;
import nettee.article.type.ArticleStatus;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Set;

public interface ArticleReadByStatusesUseCase {
    Page<ArticleSummary> findByStatuses(Set<ArticleStatus> statuses, Instant lastCreatedAt, int size);
}
