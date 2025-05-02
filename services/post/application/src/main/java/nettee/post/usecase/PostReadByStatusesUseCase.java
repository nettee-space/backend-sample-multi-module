package nettee.post.usecase;

import nettee.post.PostQueryModels.PostSummary;
import nettee.post.type.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostReadByStatusesUseCase {
    Page<PostSummary> findByStatuses(Set<PostStatus> statuses, Pageable pageable);
}
