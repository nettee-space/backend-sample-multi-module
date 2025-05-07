package nettee.draft;

import lombok.Builder;
import nettee.article.type.DraftStatus;

import java.time.Instant;
public final class DraftQueryModels {
    private DraftQueryModels() {
    }

    @Builder
    public record DraftDetail(
            Long id,
            String title,
            String content,
            DraftStatus status,
            Instant createdAt,
            Instant updatedAt

    ) {
    }
    @Builder
    public record DraftSummary(
            Long id,
            String title,
            DraftStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
