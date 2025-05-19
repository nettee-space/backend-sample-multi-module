package nettee.comment.model;

import java.util.List;
import lombok.Builder;

import java.time.Instant;
import nettee.comment.domain.type.CommentStatus;
import nettee.reply.model.ReplyQueryModels.ReplyDetail;

public final class CommentQueryModels {

    private CommentQueryModels() {
    }

    @Builder
    public record CommentDetail(
        Long id,
        String content,
        CommentStatus status,
        Instant createdAt,
        Instant updatedAt,
        List<ReplyDetail> replies
    ) {
    }

}