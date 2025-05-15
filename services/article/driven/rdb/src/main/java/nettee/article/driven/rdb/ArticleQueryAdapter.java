package nettee.article.driven.rdb;

import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import nettee.article.driven.rdb.entity.ArticleEntity;
import nettee.article.driven.rdb.entity.type.ArticleEntityStatus;
import nettee.article.driven.rdb.persistence.mapper.ArticleEntityMapper;
import nettee.article.application.port.ArticleQueryPort;
import nettee.article.domain.type.ArticleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import static nettee.article.driven.rdb.entity.QArticleEntity.articleEntity;

@Repository
public class ArticleQueryAdapter extends QuerydslRepositorySupport implements ArticleQueryPort {
    private final ArticleEntityMapper articleEntityMapper;
    private final Map<Set<ArticleStatus>, Set<ArticleEntityStatus>> statusMap = new ConcurrentHashMap<>();

    public ArticleQueryAdapter(
            ArticleEntityMapper articleEntityMapper) {
        super(ArticleEntity.class);
        this.articleEntityMapper = articleEntityMapper;
    }

    @Override
    public Optional<ArticleDetail> findById(Long id) {
        return articleEntityMapper.toOptionalArticleDetail(
                getQuerydsl().createQuery()
                        .select(articleEntity)
                        .from(articleEntity)
                        .where(articleEntity.id.eq(id)
                        ).fetchOne()
        );
    }

    @Override
    public Page<ArticleSummary> findAllAfter(Instant lastCreatedAt, int size) {
        var query = getQuerydsl().createQuery()
                .select(articleEntity)
                .from(articleEntity)
                .where(articleEntity.createdAt.gt(lastCreatedAt))
                .orderBy(articleEntity.createdAt.desc())
                .limit(size);
        var result = query.fetch();

        var totalCount  = getQuerydsl().createQuery()
                        .select(articleEntity.count())
                        .from(articleEntity)
                        .where();

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(articleEntityMapper::toArticleSummary)
                        .toList(),
                PageRequest.of(0, size),
                totalCount::fetchOne
        );
    }

    @Override
    public Page<ArticleSummary> findByStatusesAfter(Set<ArticleStatus> statuses, Instant lastCreatedAt, int size) {
        var articleEntityStatuses = statusMap.computeIfAbsent(
                statuses,
                (ignore) -> statuses.stream()
                        .map(ArticleEntityStatus::valueOf)
                        .collect(Collectors.toSet())
        );

        var query = getQuerydsl().createQuery()
                .select(articleEntity)
                .from(articleEntity)
                .where(
                        articleEntity.createdAt.gt(lastCreatedAt),
                        articleEntity.status.in(articleEntityStatuses)
                )
                .orderBy(articleEntity.createdAt.desc())
                .limit(size);
        var result = query.fetch();

        var totalCountQuery = getQuerydsl().createQuery()
                .select(articleEntity.count())
                .from(articleEntity)
                .where(
                    articleEntity.createdAt.gt(lastCreatedAt),
                    articleEntity.status.in(articleEntityStatuses)
                );

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(articleEntityMapper::toArticleSummary)
                        .toList(),
                PageRequest.of(0, size),
                totalCountQuery::fetchOne
        );
    }
}
