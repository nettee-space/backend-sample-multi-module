package nettee.post.port;

import nettee.post.Post;
import nettee.post.PostQueryModels.PostDetail;
import nettee.post.PostQueryModels.PostSummary;
import nettee.post.type.PostStatus;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostQueryPort {
    Optional<PostDetail> findById(Long id);
    Page<PostSummary> findAllAfter(Instant lastCreatedAt, int size);
    Page<PostSummary> findByStatusesAfter(Set<PostStatus> statuses, Instant lastCreatedAt, int size);

}
