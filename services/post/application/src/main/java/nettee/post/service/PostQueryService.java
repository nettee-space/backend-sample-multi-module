package nettee.post.service;

import lombok.RequiredArgsConstructor;
import nettee.post.PostQueryModels.PostDetail;
import nettee.post.PostQueryModels.PostSummary;
import nettee.post.port.PostQueryPort;
import nettee.post.type.PostStatus;
import nettee.post.usecase.PostReadByStatusesUseCase;
import nettee.post.usecase.PostReadUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostQueryService implements PostReadUseCase, PostReadByStatusesUseCase {
    private final PostQueryPort postQueryPort;
    @Override
    public Optional<PostDetail> getPost(Long id) {
        return postQueryPort.findById(id);
    }
    @Override
    public Page<PostSummary> getAllPost(Instant lastCreatedAt, int size) {
        return postQueryPort.findAllAfter(lastCreatedAt, size);
    }
    @Override
    public Page<PostSummary> findByStatuses(Set<PostStatus> statuses, Instant lastCreatedAt, int size) {
        return postQueryPort.findByStatusesAfter(statuses, lastCreatedAt, size);
    }

}
