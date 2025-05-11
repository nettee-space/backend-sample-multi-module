package nettee.comment.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.comment.entity.type.CommentEntityStatus;
import nettee.comment.entity.type.CommentEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comment")
public class CommentEntity extends LongBaseTimeEntity {

    public String content;

    @Convert(converter = CommentEntityStatusConverter.class)
    public CommentEntityStatus status;

    @Builder
    public CommentEntity(String content, CommentEntityStatus status) {
        this.content = content;
        this.status = status;
    }
}
