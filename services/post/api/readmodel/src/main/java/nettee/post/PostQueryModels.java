package nettee.post;

import lombok.Builder;
import nettee.post.type.PostStatus;

import java.time.Instant;
public final class PostQueryModels {
    private PostQueryModels() {
    }

    @Builder
    public record PostDetail(
            Long id,
            String title,
            String content,
            PostStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }

    @Builder
    public record PostSummary(
            Long id,
            String title,
            PostStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
