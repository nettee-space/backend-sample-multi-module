package nettee.post.service;

import lombok.RequiredArgsConstructor;
import nettee.post.Post;
import nettee.post.port.PostCommandPort;
import nettee.post.type.PostStatus;
import nettee.post.usecase.PostCreateUseCase;
import nettee.post.usecase.PostDeleteUseCase;
import nettee.post.usecase.PostUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommandService implements PostCreateUseCase, PostUpdateUseCase, PostDeleteUseCase {
    private final PostCommandPort postCommandPort;
    @Override
    public Post createPost(Post post) {
        return postCommandPort.create(post);
    }
    @Override
    public Post updatePost(Post post) {
        return postCommandPort.update(post);
    }

    @Override
    public void deletePost(Long id) {
        postCommandPort.updateStatus(id, PostStatus.DELETED);
    }
}
