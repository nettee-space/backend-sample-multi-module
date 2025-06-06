package nettee.article.application.port;

import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import nettee.article.domain.type.ArticleStatus;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

public interface ArticleQueryPort {
    Optional<ArticleDetail> findById(Long id);
    Page<ArticleSummary> findAllAfter(Instant lastCreatedAt, int size);
    Page<ArticleSummary> findByStatusesAfter(Set<ArticleStatus> statuses, Instant lastCreatedAt, int size);

}
