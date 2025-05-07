package nettee.article.web.dto;

import lombok.Builder;
import nettee.article.ArticleQueryModels.ArticleDetail;

public class ArticleQueryDto {
    private ArticleQueryDto() {

    }

    @Builder
    public record ArticleDetailResponse(
            ArticleDetail articleDetail
    ) {
    }
}
