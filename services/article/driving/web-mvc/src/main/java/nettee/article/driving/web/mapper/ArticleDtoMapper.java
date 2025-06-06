package nettee.article.driving.web.mapper;

import nettee.article.domain.Article;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCreateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateTotalViewsCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateTotalSharesCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateTotalLikesCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {
    Article toDomain(ArticleCreateCommand command);
    Article toDomain(Long id, ArticleUpdateCommand command);
    Article toDomain(Long id, ArticleUpdateTotalSharesCommand command);
    Article toDomain(Long id, ArticleUpdateTotalLikesCommand command);
    Article toDomain(Long id, ArticleUpdateTotalViewsCommand command);
}
