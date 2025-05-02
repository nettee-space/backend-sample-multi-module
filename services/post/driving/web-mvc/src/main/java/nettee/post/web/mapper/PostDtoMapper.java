package nettee.post.web.mapper;

import nettee.post.Post;
import nettee.post.PostQueryModels.PostDetail;
import nettee.post.type.PostStatus;
import nettee.post.web.dto.PostCommandDto.PostUpdateCommand;
import nettee.post.web.dto.PostCommandDto.PostCreateCommand;
import nettee.post.web.dto.PostCommandDto.PostUpdateTotalSharesCommand;
import nettee.post.web.dto.PostCommandDto.PostUpdateTotalLikesCommand;
import nettee.post.web.dto.PostCommandDto.PostUpdateTotalViewsCommand;
import nettee.post.web.dto.PostQueryDto.PostDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostDtoMapper {
    Post toDomain(PostCreateCommand command);
    Post toDomain(Long id, PostUpdateCommand command);
    Post toDomain(Long id, PostUpdateTotalSharesCommand command);
    Post toDomain(Long id, PostUpdateTotalLikesCommand command);
    Post toDomain(Long id, PostUpdateTotalViewsCommand command);
    PostDetailResponse toDtoDetail(PostDetail board);
}
