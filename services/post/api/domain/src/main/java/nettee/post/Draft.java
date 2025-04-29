package nettee.post;

import nettee.post.type.DraftStatus;
import java.time.Instant;

public class Draft {
    private Long id;
    private String title;
    private String content;
    private DraftStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Long blogId;
    private Long postId;
}