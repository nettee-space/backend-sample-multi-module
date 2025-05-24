package nettee.article.application.port;

import nettee.article.domain.Article;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.domain.type.ArticleStatus;

import java.util.Optional;

public interface ArticleCommandPort {
    Optional<ArticleDetail> findById(Long id);
    Article save(Article article);
    Article update(Article article);
    void updateStatus(Long id, ArticleStatus articleStatus);
}
