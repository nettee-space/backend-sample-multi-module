package nettee.reply.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.reply.entity.type.ReplyEntityStatus;
import nettee.reply.entity.type.ReplyEntityStatusConverter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "reply")
public class ReplyEntity extends LongBaseTimeEntity {

    public String content;

    @Convert(converter = ReplyEntityStatusConverter.class)
    public ReplyEntityStatus status;

    @Builder
    public ReplyEntity(String content, ReplyEntityStatus status) {
        this.content = content;
        this.status = status;
    }
}
