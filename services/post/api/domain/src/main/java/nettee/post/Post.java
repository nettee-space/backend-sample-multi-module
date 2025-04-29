package nettee.post;

import nettee.post.type.PostStatus;
import java.time.Instant;

public class Post {
    private Long id;
    private String title;
    private String content;
    private PostStatus status;
    private Integer totalViews;
    private Integer totalLikes;
    private Integer totalShares;
    private Instant createdAt;
    private Instant updatedAt;
    private Long blogId;
}
