package nettee.post;

import lombok.RequiredArgsConstructor;
import nettee.post.entity.type.PostEntityStatus;
import nettee.post.persistence.mapper.PostEntityMapper;
import nettee.post.port.PostCommandPort;
import nettee.post.type.PostStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import static nettee.post.PostCommandErrorCode.DEFAULT;
import static nettee.post.PostCommandErrorCode.POST_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class PostCommandAdapter implements PostCommandPort {
    private final PostJpaRepository postJpaRepository;
    private final PostEntityMapper postEntityMapper;

    @Override
    public Optional<PostQueryModels.PostDetail> findById(Long id) {
        var post = postJpaRepository.findById(id)
                .orElseThrow(POST_NOT_FOUND::exception);
        return postEntityMapper.toOptionalPostDetail(post);
    }

    @Override
    public Post create(Post post) {
        var postEntity = postEntityMapper.toEntity(post);
        try{
            var newPost = postJpaRepository.save(postEntity);
            postJpaRepository.flush();
            return postEntityMapper.toDomain(newPost);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Post update(Post post) {
        var existPost = postJpaRepository.findById(post.getId())
                            .orElseThrow(POST_NOT_FOUND::exception);
        existPost.preparePostEntityUpdate()
                .title(existPost.getTitle())
                .content(existPost.getContent())
                .totalLikes(existPost.getTotalLikes())
                .totalViews(existPost.getTotalViews())
                .totalShares(existPost.getTotalShares())
                .update();

        return postEntityMapper.toDomain(postJpaRepository.save(existPost));
    }

    @Override
    public void updateStatus(Long id, PostStatus postStatus) {
        var post = postJpaRepository.findById(id)
                    .orElseThrow(POST_NOT_FOUND::exception);
        post.preparePostEntityStatusUpdate()
                .status(PostEntityStatus.valueOf(postStatus))
                .updateStatus();
        postJpaRepository.save(post);
    }
}
