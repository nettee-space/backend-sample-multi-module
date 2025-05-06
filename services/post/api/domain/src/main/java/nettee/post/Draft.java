package nettee.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.post.type.DraftStatus;

import java.time.Instant;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Draft {
    private Long id;
    private String title;
    private String content;
    private DraftStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Long blogId;
    private Long postId;

    @Builder(
            builderClassName = "updateDraftBuilder",
            builderMethodName = "prepareDraftUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        this.title = title;
        this.content = content;
        this.updatedAt = Instant.now();
    }

    public void softDelete() { this.status = DraftStatus.DELETED; };
}