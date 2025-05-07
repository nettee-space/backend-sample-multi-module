package nettee.draft.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.draft.entity.type.DraftEntityStatus;
import nettee.draft.entity.type.DraftEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "draft")
public class DraftEntity extends LongBaseTimeEntity {
    public String title;
    public String content;

    @Convert(converter = DraftEntityStatusConverter.class)
    public DraftEntityStatus status;

    @Builder
    public DraftEntity(String title, String content, DraftEntityStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    @Builder(
            builderClassName = "updateDraftEntityBuilder",
            builderMethodName = "prepareDraftEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content, Integer totalLikes, Integer totalViews, Integer totalShares) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "status cannot be null");

        this.title = title;
        this.content = content;
    }

    @Builder(
            builderClassName = "updateStatusDraftEntityBuilder",
            builderMethodName = "prepareDraftEntityStatusUpdate",
            buildMethodName = "updateStatus"
    )
    public void updateStatus(DraftEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");

        this.status = status;
    }
}
