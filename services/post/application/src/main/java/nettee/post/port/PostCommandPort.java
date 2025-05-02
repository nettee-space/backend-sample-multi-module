package nettee.post.port;

import nettee.post.Post;
import nettee.post.PostQueryModels.PostDetail;
import nettee.post.type.PostStatus;
import java.util.Optional;

public interface PostCommandPort {
    Optional<PostDetail> findById(Long id);
    Post create(Post post);
    Post update(Post post);
    void updateStatus(Long id, PostStatus postStatus);
}
