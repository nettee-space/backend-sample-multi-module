package nettee.post.port;

import nettee.post.Post;
import nettee.post.type.PostStatus;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostQueryPort {
    Optional<Post> findById(Long id);
    List<Post> findAllAfter(Instant lastCreatedAt, int size);
    List<Post> findByStatusesAfter(Set<PostStatus> statuses, Instant lastCreatedAt, int size);

}
