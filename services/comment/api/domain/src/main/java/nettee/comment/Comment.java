package nettee.comment;

import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.comment.type.CommentStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;

    private Long boardId;

    private String content;

    private CommentStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    @Builder(
        builderClassName = "updateCommentBuilder",
        builderMethodName = "prepareUpdate",
        buildMethodName = "update"
    )
    public void update(String content) {
        Objects.requireNonNull(content, "content cannot be null");

        this.content = content;
        this.updatedAt = Instant.now();
    }

    public void softDelete() {
        this.status = CommentStatus.REMOVED;
    }

}
