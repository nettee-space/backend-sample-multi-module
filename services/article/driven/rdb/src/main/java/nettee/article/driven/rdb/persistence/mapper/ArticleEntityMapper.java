package nettee.article.driven.rdb.persistence.mapper;

import nettee.article.domain.Article;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import nettee.article.driven.rdb.entity.ArticleEntity;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {
    Article toDomain(ArticleEntity articleEntity);
    ArticleEntity toEntity(Article article);
    ArticleDetail toArticleDetail(ArticleEntity articleEntity);
    ArticleSummary toArticleSummary(ArticleEntity articleEntity);
    default Optional<Article> toOptionalDomain(ArticleEntity articleEntity) {
        return Optional.ofNullable(toDomain(articleEntity));
    }
    default Optional<ArticleDetail> toOptionalArticleDetail(ArticleEntity articleEntity) {
        return Optional.ofNullable(toArticleDetail(articleEntity));
    }
    default Optional<ArticleSummary> toOptionalArticleSummary(ArticleEntity articleEntity) {
        return Optional.ofNullable(toArticleSummary(articleEntity));
    }

}
