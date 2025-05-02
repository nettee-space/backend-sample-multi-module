package nettee.post.web.dto;

import lombok.Builder;
import nettee.post.PostQueryModels.PostDetail;

public class PostQueryDto {
    private PostQueryDto() {

    }

    @Builder
    public record PostDetailResponse(
            PostDetail postDetail
    ) {
    }
}
