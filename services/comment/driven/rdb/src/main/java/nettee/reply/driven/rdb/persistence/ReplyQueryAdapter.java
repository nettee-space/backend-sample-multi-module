package nettee.reply.driven.rdb.persistence;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nettee.reply.driven.rdb.persistence.mapper.ReplyEntityMapper;
import nettee.reply.driven.rdb.entity.ReplyEntity;
import nettee.reply.model.ReplyQueryModels.ReplyDetail;
import nettee.reply.application.port.ReplyQueryRepositoryPort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static nettee.reply.driven.rdb.entity.QReplyEntity.replyEntity;

@Repository
public class ReplyQueryAdapter extends QuerydslRepositorySupport implements ReplyQueryRepositoryPort {

    private final ReplyEntityMapper replyEntityMapper;

    public ReplyQueryAdapter(ReplyEntityMapper replyEntityMapper) {
        super(ReplyEntity.class);
        this.replyEntityMapper = replyEntityMapper;
    }

    @Override
    public Optional<ReplyDetail> findById(Long id) {
        return replyEntityMapper.toOptionalReplyDetail(
            getQuerydsl().createQuery()
                .select(replyEntity)
                .from(replyEntity)
                .where(replyEntity.id.eq(id))
                .fetchOne()
        );
    }

    @Override
    public List<ReplyDetail> findPageByCommentId(Long commentId, int offset, int size) {
        var entityList = getQuerydsl().createQuery()
            .select(replyEntity)
            .from(replyEntity)
            .where(replyEntity.commentId.eq(commentId))
            .offset(offset)
            .limit(size)
            .orderBy(replyEntity.createdAt.asc())
            .fetch();

        var result = entityList.stream()
            .map(entity -> replyEntityMapper.toReplyDetail(entity))
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<ReplyDetail> findPageByCommentIdAfter(Long commentId, Instant createdAt, int size) {
        var entityList = getQuerydsl().createQuery()
            .select(replyEntity)
            .from(replyEntity)
            .where(
                replyEntity.commentId.eq(commentId).and(
                replyEntity.createdAt.after(createdAt)))
            .offset(0)
            .limit(size)
            .orderBy(replyEntity.createdAt.asc())
            .fetch();

        var result = entityList.stream()
            .map(entity -> replyEntityMapper.toReplyDetail(entity))
            .collect(Collectors.toList());

        return result;
    }
}
