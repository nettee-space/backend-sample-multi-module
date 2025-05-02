package nettee.post.usecase;

import nettee.post.PostQueryModels.PostDetail;

public interface PostReadUseCase {
    PostDetail getPost(Long id);
}
