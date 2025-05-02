package nettee.post.persistence.mapper;

import nettee.post.Post;
import nettee.post.PostQueryModels.PostSummary;
import nettee.post.PostQueryModels.PostDetail;
import nettee.post.entity.PostEntity;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PostEntityMapper {
    Post toDomain(PostEntity postEntity);
    PostEntity toEntity(Post post);
    PostDetail toPostDetail(PostEntity postEntity);
    PostSummary toPostSummary(PostEntity postEntity);
    default Optional<Post> toOptionalDomain(PostEntity postEntity) {
        return Optional.ofNullable(toDomain(postEntity));
    }
    default Optional<PostDetail> toOptionalPostDetail(PostEntity postEntity) {
        return Optional.ofNullable(toPostDetail(postEntity));
    }
    default Optional<PostSummary> toOptionalPostSummary(PostEntity postEntity) {
        return Optional.ofNullable(toPostSummary(postEntity));
    }

}
