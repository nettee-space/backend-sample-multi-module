package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.port.ArticleQueryPort;
import nettee.article.ArticleQueryModels.ArticleDetail;
import nettee.article.ArticleQueryModels.ArticleSummary;
import nettee.article.domain.type.ArticleStatus;
import nettee.article.usecase.ArticleReadByStatusesUseCase;
import nettee.article.usecase.ArticleReadUseCase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArticleQueryService implements ArticleReadUseCase, ArticleReadByStatusesUseCase {
    private final ArticleQueryPort articleQueryPort;
    @Override
    public Optional<ArticleDetail> getArticle(Long id) {
        return articleQueryPort.findById(id);
    }
    @Override
    public Page<ArticleSummary> getAllArticle(Instant lastCreatedAt, int size) {
        return articleQueryPort.findAllAfter(lastCreatedAt, size);
    }
    @Override
    public Page<ArticleSummary> findByStatuses(Set<ArticleStatus> statuses, Instant lastCreatedAt, int size) {
        return articleQueryPort.findByStatusesAfter(statuses, lastCreatedAt, size);
    }

}
