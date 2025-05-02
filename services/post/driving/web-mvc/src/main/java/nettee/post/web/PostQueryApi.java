package nettee.post.web;

import lombok.RequiredArgsConstructor;
import nettee.post.PostQueryModels.PostSummary;
import nettee.post.type.PostStatus;
import nettee.post.usecase.PostReadByStatusesUseCase;
import nettee.post.usecase.PostReadUseCase;
import nettee.post.web.dto.PostQueryDto.PostDetailResponse;
import nettee.post.web.mapper.PostDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostQueryApi {
    private final PostReadUseCase postReadUseCase;
    private final PostReadByStatusesUseCase postReadByStatusesUseCase;
    private final PostDtoMapper mapper;

    @GetMapping("/{postId}")
    public PostDetailResponse getPost(@PathVariable("postId") long postId) {
        var post = postReadUseCase.getPost(postId);
        return mapper.toDtoDetail(post);
    }

    @GetMapping
    public Page<PostSummary> getPostsByStatuses(@RequestParam(defaultValue = "ACTIVE, SUSPENDED") Set<PostStatus> statuses, Instant lastCreatedAt, int size) {
        return postReadByStatusesUseCase.findByStatuses(statuses, lastCreatedAt, size);
    }

}