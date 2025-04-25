package nettee.post;

import nettee.post.type.PostStatus;

import java.time.Instant;
public class Post {
    private Long id;
    private String title;
    private String content;
    private PostStatus status;
    private Integer total_views;
    private Integer total_likes;
    private Integer total_shares;
    private Instant create_at;
    private Instant update_at;
    private Long blog_id;
}
