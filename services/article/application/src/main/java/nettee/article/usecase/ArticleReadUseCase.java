package nettee.article.usecase;

import nettee.article.ArticleQueryModels.ArticleDetail;
import nettee.article.ArticleQueryModels.ArticleSummary;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Optional;

public interface ArticleReadUseCase {
    Optional<ArticleDetail> getArticle(Long id);
    Page<ArticleSummary> getAllArticle(Instant lastCreatedAt, int size);
}
