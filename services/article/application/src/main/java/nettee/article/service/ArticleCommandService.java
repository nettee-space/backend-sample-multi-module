package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.Article;
import nettee.article.usecase.ArticleCreateUseCase;
import nettee.article.usecase.ArticleUpdateUseCase;
import nettee.article.port.ArticleCommandPort;
import nettee.article.type.ArticleStatus;
import nettee.article.usecase.ArticleDeleteUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommandService implements ArticleCreateUseCase, ArticleUpdateUseCase, ArticleDeleteUseCase {
    private final ArticleCommandPort articleCommandPort;
    @Override
    public Article createArticle(Article article) {
        return articleCommandPort.create(article);
    }
    @Override
    public Article updateArticle(Article article) {
        return articleCommandPort.update(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleCommandPort.updateStatus(id, ArticleStatus.DELETED);
    }
}
