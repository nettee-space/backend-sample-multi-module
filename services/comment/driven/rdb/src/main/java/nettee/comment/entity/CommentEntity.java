package nettee.comment.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.comment.entity.type.CommentEntityStatus;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comment")
public class CommentEntity extends LongBaseTimeEntity {

    @Id
    private Long id;

    private String content;

    @Convert
    private CommentEntityStatus status;
}
