package nettee.board.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.board.driven.rdb.entity.type.BoardEntityStatus;
import nettee.board.driven.rdb.entity.type.BoardEntityStatusConverter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board")
public class BoardEntity extends LongBaseTimeEntity {
    public String title;
    public String content;

    @Convert(converter = BoardEntityStatusConverter.class)
    public BoardEntityStatus status;

    @Builder
    public BoardEntity(String title, String content, BoardEntityStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }
}