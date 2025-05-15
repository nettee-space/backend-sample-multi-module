package nettee.article.port;

import nettee.article.domain.Article;
import nettee.article.ArticleQueryModels.ArticleDetail;
import nettee.article.domain.type.ArticleStatus;

import java.util.Optional;

public interface ArticleCommandPort {
    Optional<ArticleDetail> findById(Long id);
    Article create(Article article);
    Article update(Article article);
    void updateStatus(Long id, ArticleStatus articleStatus);
}
