package nettee.reply.driven.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.reply.driven.rdb.entity.type.ReplyEntityStatus;
import nettee.reply.driven.rdb.entity.type.ReplyEntityStatusConverter;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity(name = "reply")
public class ReplyEntity extends LongBaseTimeEntity {

    @Column(nullable = false)
    public Long commentId;

    public String content;

    @Convert(converter = ReplyEntityStatusConverter.class)
    public ReplyEntityStatus status;

    @Builder
    public ReplyEntity(Long commentId, String content, ReplyEntityStatus status) {
        this.commentId = commentId;
        this.content = content;
        this.status = status;
    }

    @Builder(
        builderClassName = "updateReplyEntityBuilder",
        builderMethodName = "prepareReplyEntityUpdate",
        buildMethodName = "update"
    )
    public void update(String content) {
        Objects.requireNonNull(content, "Content cannot be null!");

        this.content = content;
    }

    @Builder(
        builderClassName = "updateStatusReplyEntityBuilder",
        builderMethodName = "prepareReplyEntityStatusUpdate",
        buildMethodName = "updateStatus"
    )
    public void updateStatus(ReplyEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");

        this.status = status;
    }
}
