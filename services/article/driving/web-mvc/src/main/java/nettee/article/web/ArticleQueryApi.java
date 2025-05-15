package nettee.article.web;

import lombok.RequiredArgsConstructor;
import nettee.article.ArticleQueryModels.ArticleDetail;
import nettee.article.ArticleQueryModels.ArticleSummary;
import nettee.article.domain.type.ArticleStatus;
import nettee.article.application.usecase.ArticleReadByStatusesUseCase;
import nettee.article.application.usecase.ArticleReadUseCase;
import nettee.article.web.mapper.ArticleDtoMapper;
import nettee.article.web.dto.ArticleQueryDto.ArticleDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Set;

import static nettee.article.ArticleQueryErrorCode.ARTICLE_NOT_FOUND;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleQueryApi {
    private final ArticleReadUseCase articleReadUseCase;
    private final ArticleReadByStatusesUseCase articleReadByStatusesUseCase;
    private final ArticleDtoMapper mapper;

    @GetMapping("/{articleId}")
    public ArticleDetailResponse getArticle(@PathVariable("articleId") long articleId) {
        ArticleDetail articleDetail = articleReadUseCase.getArticle(articleId)
                .orElseThrow(ARTICLE_NOT_FOUND::exception);
        return new ArticleDetailResponse(articleDetail);
    }

    @GetMapping
    public Page<ArticleSummary> getArticlesByStatuses(
            @RequestParam(defaultValue = "ACTIVE, SUSPENDED") Set<ArticleStatus> statuses,
            @RequestParam Instant lastCreatedAt,
            @RequestParam(defaultValue = "10") int size) {
        return articleReadByStatusesUseCase.findByStatuses(statuses, lastCreatedAt, size);
    }
}