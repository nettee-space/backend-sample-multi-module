package nettee.reply.model;

import java.time.Instant;
import lombok.Builder;
import nettee.reply.domain.type.ReplyStatus;

public final class ReplyQueryModels {

    private ReplyQueryModels() {
    }

    @Builder
    public record ReplyDetail(
        Long id,
        Long commentId,
        String content,
        ReplyStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
    }

}