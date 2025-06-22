package nettee.comment.driven.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.comment.driven.rdb.entity.type.CommentEntityStatus;
import nettee.comment.driven.rdb.entity.type.CommentEntityStatusConverter;
import nettee.comment.driven.rdb.entity.type.ReplyListConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.reply.driven.rdb.entity.ReplyEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity(name = "comment")
public class CommentEntity extends LongBaseTimeEntity {

    @Column(nullable = false)
    public Long boardId;

    public String content;

    @Convert(converter = CommentEntityStatusConverter.class)
    public CommentEntityStatus status;

    @Convert(converter = ReplyListConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    public List<ReplyEntity> replies = new ArrayList<>();

    @Builder
    public CommentEntity(Long boardId, String content, CommentEntityStatus status, List<ReplyEntity> replies) {
        this.boardId = boardId;
        this.content = content;
        this.status = status;
        this.replies = replies;
    }

    @Builder(
        builderClassName = "updateCommentEntityBuilder",
        builderMethodName = "prepareCommentEntityUpdate",
        buildMethodName = "update"
    )
    public void update(String content) {
        Objects.requireNonNull(content, "Content cannot be null!");

        this.content = content;
    }

    @Builder(
        builderClassName = "updateStatusCommentEntityBuilder",
        builderMethodName = "prepareCommentEntityStatusUpdate",
        buildMethodName = "updateStatus"
    )
    public void updateStatus(CommentEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");

        this.status = status;
    }

    @Builder(
        builderClassName = "updateReplyListBuilder",
        builderMethodName = "prepareReplyListUpdate",
        buildMethodName = "updateReplyList"
    )
    public void updateReplyList(List<ReplyEntity> replies) {
        Objects.requireNonNull(replies, "replies cannot be null");

        this.replies = replies;
    }
}
