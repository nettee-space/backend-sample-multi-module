package nettee.comment.driven.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import nettee.comment.driven.rdb.entity.type.CommentEntityStatus;
import nettee.comment.driven.rdb.entity.type.CommentEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity(name = "comment")
public class CommentEntity extends LongBaseTimeEntity {

    @Column(nullable = false)
    public Long boardId;

    public String content;

    @Convert(converter = CommentEntityStatusConverter.class)
    public CommentEntityStatus status;

    @Builder
    public CommentEntity(Long boardId, String content, CommentEntityStatus status) {
        this.boardId = boardId;
        this.content = content;
        this.status = status;
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
}
