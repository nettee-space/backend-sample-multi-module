package nettee.article.application.usecase;

import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Optional;

public interface ArticleReadUseCase {
    Optional<ArticleDetail> getArticle(Long id);
    Page<ArticleSummary> getAllArticle(Instant lastCreatedAt, int size);
}
