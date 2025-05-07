package nettee.draft;

import nettee.draft.entity.DraftEntity;
import nettee.draft.entity.type.DraftEntityStatus;
import nettee.draft.persistence.mapper.DraftEntityMapper;
import nettee.draft.port.DraftQueryPort;
import nettee.article.type.DraftStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import static nettee.draft.entity.QDraftEntity.draftEntity;

@Repository
public class DraftQueryAdapter extends QuerydslRepositorySupport implements DraftQueryPort {
    private final DraftEntityMapper draftEntityMapper;
    private final Map<Set<DraftStatus>, Set<DraftEntityStatus>> statusMap = new ConcurrentHashMap<>();

    public DraftQueryAdapter(
            DraftEntityMapper draftEntityMapper) {
        super(DraftEntity.class);
        this.draftEntityMapper = draftEntityMapper;
    }

    @Override
    public Optional<DraftQueryModels.DraftDetail> findById(Long id) {
        return draftEntityMapper.toOptionalDraftDetail(
                getQuerydsl().createQuery()
                        .select(draftEntity)
                        .from(draftEntity)
                        .where(draftEntity.id.eq(id)
                        ).fetchOne()
        );
    }

    @Override
    public Page<DraftQueryModels.DraftSummary> findAll(Pageable pageable) {
        var query = getQuerydsl().createQuery()
                .select(draftEntity)
                .from(draftEntity)
                .where();

        pageable.getSort().forEach(order ->
                query.orderBy(order.isAscending() ?
                        draftEntity.createdAt.asc() :
                        draftEntity.createdAt.desc())
        );

        var result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var totalCount  = getQuerydsl().createQuery()
                        .select(draftEntity.count())
                        .from(draftEntity)
                        .where();

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(draftEntityMapper::toDraftSummary)
                        .toList(),
                pageable,
                totalCount::fetchOne
        );
    }

    @Override
    public Page<DraftQueryModels.DraftSummary> findByStatuses(Set<DraftStatus> statuses, Pageable pageable) {
        var draftEntityStatuses = statusMap.computeIfAbsent(
                statuses,
                (ignore) -> statuses.stream()
                        .map(DraftEntityStatus::valueOf)
                        .collect(Collectors.toSet())
        );

        var query = getQuerydsl().createQuery()
                .select(draftEntity)
                .from(draftEntity)
                .where(draftEntity.status.in(draftEntityStatuses));

        pageable.getSort().forEach(order ->
                query.orderBy(order.isAscending() ?
                        draftEntity.createdAt.asc() :
                        draftEntity.createdAt.desc())
        );

        var result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var totalCount = getQuerydsl().createQuery()
                .select(draftEntity.count())
                .from(draftEntity)
                .where(draftEntity.status.in(draftEntityStatuses));

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(draftEntityMapper::toDraftSummary)
                        .toList(),
                pageable,
                totalCount::fetchOne
        );
    }
}
