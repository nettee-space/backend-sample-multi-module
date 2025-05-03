package nettee.reply.model;

import java.time.Instant;
import lombok.Builder;
import nettee.reply.type.ReplyStatus;

public final class ReplyQueryModels {

    private ReplyQueryModels() {
    }

    @Builder
    public record ReplyDetail(
        Long id,
        Long parentId,
        String content,
        ReplyStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
    }

    @Builder
    public record ReplySummary(
        Long id,
        Long parentId,
        String title,
        ReplyStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
    }
}