package nettee.reply.driven.rdb.persistence;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import nettee.reply.domain.Reply;
import nettee.reply.driven.rdb.entity.type.ReplyEntityStatus;
import nettee.reply.driven.rdb.persistence.mapper.ReplyEntityMapper;
import nettee.reply.application.port.ReplyCommandRepositoryPort;
import nettee.reply.domain.type.ReplyStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static nettee.reply.exception.ReplyCommandErrorCode.DEFAULT;
import static nettee.reply.exception.ReplyCommandErrorCode.REPLY_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ReplyCommandAdapter implements ReplyCommandRepositoryPort {

    private final ReplyJpaRepository replyJpaRepository;
    private final ReplyEntityMapper replyEntityMapper;

    @Override
    public Reply save(Reply reply) {
        var replyEntity = replyEntityMapper.toEntity(reply);
        try {
            var newReply = replyJpaRepository.save(replyEntity);
            replyJpaRepository.flush();
            return replyEntityMapper.toDomain(newReply);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Reply update(Reply reply) {
        var existReply = replyJpaRepository.findById(reply.getId())
            .orElseThrow(REPLY_NOT_FOUND::exception);

        existReply.prepareReplyEntityUpdate()
            .content(reply.getContent())
            .update();

        return replyEntityMapper.toDomain(existReply);
    }

    @Override
    public Reply findById(Long id) {
        var replyEntity = replyJpaRepository.findById(id)
            .orElseThrow(REPLY_NOT_FOUND::exception);

        return replyEntityMapper.toDomain(replyEntity);
    }

    @Override
    public void updateStatus(Long id, ReplyStatus status) {
        var existReply = replyJpaRepository.findById(id)
            .orElseThrow(REPLY_NOT_FOUND::exception);

        existReply.prepareReplyEntityStatusUpdate()
            .status(ReplyEntityStatus.valueOf(status))
            .updateStatus();
    }

    @Override
    public Reply findFirstByCommentIdAfter(Long commentId, Instant createdAt) {
        var replyEntity = replyJpaRepository.findByCommentIdAndCreatedAtAfter(commentId, createdAt, ReplyEntityStatus.ACTIVE,
            PageRequest.of(0,1));

        return replyEntityMapper.toDomain(replyEntity.get(0));
    }
}
