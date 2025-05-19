package nettee.comment.driven.rdb.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nettee.comment.driven.rdb.persistence.mapper.CommentEntityMapper;
import nettee.comment.driven.rdb.entity.CommentEntity;
import nettee.comment.model.CommentQueryModels.CommentDetail;
import nettee.comment.application.port.CommentQueryRepositoryPort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static nettee.comment.driven.rdb.entity.QCommentEntity.commentEntity;

@Repository
public class CommentQueryAdapter extends QuerydslRepositorySupport implements CommentQueryRepositoryPort {

    private final CommentEntityMapper commentEntityMapper;

    public CommentQueryAdapter(CommentEntityMapper commentEntityMapper) {
        super(CommentEntity.class);
        this.commentEntityMapper = commentEntityMapper;
    }

    @Override
    public Optional<CommentDetail> findById(Long id) {
        return commentEntityMapper.toOptionalCommentDetail(
            getQuerydsl().createQuery()
                .select(commentEntity)
                .from(commentEntity)
                .where(commentEntity.id.eq(id))
                .fetchOne()
        );
    }

    @Override
    public List<CommentDetail> findPageByBoardId(Long boardId, int offset, int size) {
        var entityList = getQuerydsl().createQuery()
            .select(commentEntity)
            .from(commentEntity)
            .where(commentEntity.boardId.eq(boardId))
            .offset(offset)
            .limit(size)
            .orderBy(commentEntity.createdAt.asc())
            .fetch();

        var result = entityList.stream()
            .map(entity -> commentEntityMapper.toCommentDetail(entity))
            .collect(Collectors.toList());

        return result;
    }
}
