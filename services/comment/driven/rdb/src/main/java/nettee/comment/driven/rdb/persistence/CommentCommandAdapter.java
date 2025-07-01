package nettee.comment.driven.rdb.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nettee.comment.domain.Comment;
import nettee.comment.driven.rdb.entity.type.CommentEntityStatus;
import nettee.comment.driven.rdb.persistence.mapper.CommentEntityMapper;
import nettee.comment.application.port.CommentCommandRepositoryPort;
import nettee.comment.domain.type.CommentStatus;
import nettee.reply.domain.Reply;
import nettee.reply.driven.rdb.persistence.mapper.ReplyEntityMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.comment.exception.CommentCommandErrorCode.COMMENT_NOT_FOUND;
import static nettee.comment.exception.CommentCommandErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class CommentCommandAdapter implements CommentCommandRepositoryPort {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentEntityMapper commentEntityMapper;
    private final ReplyEntityMapper replyEntityMapper;

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
    public Comment findById(Long id) {
        var commentEntity = commentJpaRepository.findById(id)
            .orElseThrow(COMMENT_NOT_FOUND::exception);

        return commentEntityMapper.toDomain(commentEntity);
    }

    @Override
    public void updateStatus(Long id, CommentStatus status) {
        var existComment = commentJpaRepository.findById(id)
            .orElseThrow(COMMENT_NOT_FOUND::exception);

        existComment.prepareCommentEntityStatusUpdate()
            .status(CommentEntityStatus.valueOf(status))
            .updateStatus();
    }

    @Override
    public void updateReplies(Long id, List<Reply> replyList) {
        var existComment = commentJpaRepository.findById(id)
            .orElseThrow(COMMENT_NOT_FOUND::exception);

        var replies = replyEntityMapper.toEntityList(replyList);

        existComment.prepareReplyListUpdate()
            .replies(replies)
            .updateReplyList();
    }
}
