package nettee.comment.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.comment.domain.type.CommentStatus;
import nettee.reply.domain.Reply;

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

    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

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
