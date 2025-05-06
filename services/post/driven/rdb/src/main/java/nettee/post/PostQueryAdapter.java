package nettee.post;

import nettee.post.entity.PostEntity;
import nettee.post.entity.type.PostEntityStatus;
import nettee.post.persistence.mapper.PostEntityMapper;
import nettee.post.port.PostQueryPort;
import nettee.post.type.PostStatus;
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
import static nettee.post.entity.QPostEntity.postEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Repository
public class PostQueryAdapter extends QuerydslRepositorySupport implements PostQueryPort {
    private final PostEntityMapper postEntityMapper;
    private final Map<Set<PostStatus>, Set<PostEntityStatus>> statusMap = new ConcurrentHashMap<>();

    public PostQueryAdapter(
            PostEntityMapper postEntityMapper) {
        super(PostEntity.class);
        this.postEntityMapper = postEntityMapper;
    }

    @Override
    public Optional<PostQueryModels.PostDetail> findById(Long id) {
        return postEntityMapper.toOptionalPostDetail(
                getQuerydsl().createQuery()
                        .select(postEntity)
                        .from(postEntity)
                        .where(postEntity.id.eq(id)
                        ).fetchOne()
        );
    }

    @Override
    public Page<PostQueryModels.PostSummary> findAllAfter(Instant lastCreatedAt, int size) {
        var query = getQuerydsl().createQuery()
                .select(postEntity)
                .from(postEntity)
                .where(postEntity.createdAt.gt(lastCreatedAt))
                .orderBy(postEntity.createdAt.desc())
                .limit(size);
        var result = query.fetch();

        var totalCount  = getQuerydsl().createQuery()
                        .select(postEntity.count())
                        .from(postEntity)
                        .where();

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(postEntityMapper::toPostSummary)
                        .toList(),
                PageRequest.of(0, size),
                totalCount::fetchOne
        );
    }

    @Override
    public Page<PostQueryModels.PostSummary> findByStatusesAfter(Set<PostStatus> statuses, Instant lastCreatedAt, int size) {
        var postEntityStatuses = statusMap.computeIfAbsent(
                statuses,
                (ignore) -> statuses.stream()
                        .map(PostEntityStatus::valueOf)
                        .collect(Collectors.toSet())
        );

        var query = getQuerydsl().createQuery()
                .select(postEntity)
                .from(postEntity)
                .where(
                        postEntity.createdAt.gt(lastCreatedAt),
                        postEntity.status.in(postEntityStatuses)
                )
                .orderBy(postEntity.createdAt.desc())
                .limit(size);
        var result = query.fetch();

        var totalCountQuery = getQuerydsl().createQuery()
                .select(postEntity.count())
                .from(postEntity)
                .where(
                    postEntity.createdAt.gt(lastCreatedAt),
                    postEntity.status.in(postEntityStatuses)
                );

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(postEntityMapper::toPostSummary)
                        .toList(),
                PageRequest.of(0, size),
                totalCountQuery::fetchOne
        );
    }
}
