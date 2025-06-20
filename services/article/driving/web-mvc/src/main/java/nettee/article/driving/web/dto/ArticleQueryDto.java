package nettee.article.driving.web.dto;

import lombok.Builder;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;

public class ArticleQueryDto {
    private ArticleQueryDto() {

    }

    @Builder
    public record ArticleDetailResponse(
            ArticleDetail articleDetail
    ) {
    }
}
