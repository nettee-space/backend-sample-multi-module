package nettee.reply.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.reply.entity.type.ReplyEntityStatus;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comment")
public class ReplyEntity extends LongBaseTimeEntity {

    @Id
    private Long id;

    private String content;

    @Convert
    private ReplyEntityStatus status;
}
