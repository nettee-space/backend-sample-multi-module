package nettee.comment.persistence;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nettee.comment.Comment;
import nettee.comment.entity.CommentEntity;
import nettee.comment.persistence.mapper.CommentEntityMapper;
import nettee.comment.port.CommentQueryRepositoryPort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static nettee.comment.entity.QCommentEntity.commentEntity;

@Repository
public class CommentQueryAdapter extends QuerydslRepositorySupport implements CommentQueryRepositoryPort {

    private final CommentEntityMapper commentEntityMapper;

    public CommentQueryAdapter(CommentEntityMapper commentEntityMapper) {
        super(CommentEntity.class);
        this.commentEntityMapper = commentEntityMapper;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentEntityMapper.toOptionalDomain(
            getQuerydsl().createQuery()
                .select(commentEntity)
                .from(commentEntity)
                .where(commentEntity.id.eq(id))
                .fetchOne()
        );
    }

    @Override
    public List<Comment> findPageByBoardId(Long boardId, int offset, int size) {
        var entityList = getQuerydsl().createQuery()
            .select(commentEntity)
            .from(commentEntity)
            .where(commentEntity.boardId.eq(boardId))
            .offset(offset)
            .limit(size)
            .orderBy(commentEntity.createdAt.asc())
            .fetch();

        var result = entityList.stream()
            .map(entity -> commentEntityMapper.toDomain(entity))
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Comment> findPageByBoardIdAfter(Long boardId, Instant createdAt, int size) {
        var entityList = getQuerydsl().createQuery()
            .select(commentEntity)
            .from(commentEntity)
            .where(
                commentEntity.boardId.eq(boardId).and(
                commentEntity.createdAt.after(createdAt)))
            .offset(0)
            .limit(size)
            .orderBy(commentEntity.createdAt.asc())
            .fetch();

        var result = entityList.stream()
            .map(entity -> commentEntityMapper.toDomain(entity))
            .collect(Collectors.toList());

        return result;
    }
}
