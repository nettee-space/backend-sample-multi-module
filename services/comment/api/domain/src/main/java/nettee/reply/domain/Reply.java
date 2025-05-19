package nettee.reply.domain;

import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.reply.domain.type.ReplyStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    private Long id;

    private Long commentId;

    private String content;

    private ReplyStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    @Builder(
        builderClassName = "updateReplyBuilder",
        builderMethodName = "prepareUpdate",
        buildMethodName = "update"
    )
    public void update(String content) {
        Objects.requireNonNull(content, "content cannot be null");

        this.content = content;
        this.updatedAt = Instant.now();
    }

    public void softDelete() {
        this.status = ReplyStatus.REMOVED;
    }
}
