package nettee.post;


import nettee.post.type.DraftPostStatus;

import java.time.Instant;

public class DraftPost {
    private Long id;
    private String title;
    private String content;
    private DraftPostStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Long blogId;
    private Long postId;
}