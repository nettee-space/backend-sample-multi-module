package nettee.article.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nettee.article.domain.Article;
import nettee.article.application.usecase.ArticleCreateUseCase;
import nettee.article.application.usecase.ArticleUpdateUseCase;
import nettee.article.application.port.ArticleCommandPort;
import nettee.article.domain.type.ArticleStatus;
import nettee.article.application.usecase.ArticleDeleteUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandService implements ArticleCreateUseCase, ArticleUpdateUseCase, ArticleDeleteUseCase {
    private final ArticleCommandPort articleCommandPort;
    @Override
    public Article createArticle(Article article) {
        return articleCommandPort.save(article);
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
