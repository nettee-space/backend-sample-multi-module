package nettee.comment.persistence;

import lombok.RequiredArgsConstructor;
import nettee.comment.Comment;
import nettee.comment.entity.type.CommentEntityStatus;
import nettee.comment.persistence.mapper.CommentEntityMapper;
import nettee.comment.port.CommentCommandRepositoryPort;
import nettee.comment.type.CommentStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.comment.CommentCommandErrorCode.COMMENT_NOT_FOUND;
import static nettee.comment.CommentCommandErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class CommentCommandAdapter implements CommentCommandRepositoryPort {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentEntityMapper commentEntityMapper;

    @Override
    public Comment save(Comment comment) {
        var commentEntity = commentEntityMapper.toEntity(comment);
        try {
            var newComment = commentJpaRepository.save(commentEntity);
            commentJpaRepository.flush();
            return commentEntityMapper.toDomain(newComment);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Comment update(Comment comment) {
        var existComment = commentJpaRepository.findById(comment.getId())
            .orElseThrow(COMMENT_NOT_FOUND::exception);

        existComment.prepareCommentEntityUpdate()
            .content(comment.getContent())
            .update();

        return commentEntityMapper.toDomain(existComment);
    }

    @Override
    public void updateStatus(Long id, CommentStatus status) {
        var existComment = commentJpaRepository.findById(id)
            .orElseThrow(COMMENT_NOT_FOUND::exception);

        existComment.prepareCommentEntityStatusUpdate()
            .status(CommentEntityStatus.valueOf(status))
            .updateStatus();
    }
}
