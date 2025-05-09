package nettee.comment.model;

import lombok.Builder;

import java.time.Instant;
import nettee.comment.type.CommentStatus;

public final class CommentQueryModels {

    private CommentQueryModels() {
    }

    @Builder
    public record CommentDetail(
        Long id,
        String content,
        CommentStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
    }

    @Builder
    public record CommentSummary(
        Long id,
        String title,
        CommentStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
    }
}