package nettee.post.usecase;

import nettee.post.PostQueryModels.PostDetail;
import nettee.post.PostQueryModels.PostSummary;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Optional;

public interface PostReadUseCase {
    Optional<PostDetail> getPost(Long id);
    Page<PostSummary> getAllPost(Instant lastCreatedAt, int size);
}
