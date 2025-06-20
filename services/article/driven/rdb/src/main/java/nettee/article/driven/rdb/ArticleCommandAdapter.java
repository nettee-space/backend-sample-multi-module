package nettee.article.driven.rdb;

import lombok.RequiredArgsConstructor;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.domain.Article;
import nettee.article.driven.rdb.entity.type.ArticleEntityStatus;
import nettee.article.driven.rdb.persistence.mapper.ArticleEntityMapper;
import nettee.article.application.port.ArticleCommandPort;
import nettee.article.domain.type.ArticleStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import static nettee.article.exception.ArticleCommandErrorCode.DEFAULT;
import static nettee.article.exception.ArticleCommandErrorCode.ARTICLE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ArticleCommandAdapter implements ArticleCommandPort {
    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Optional<ArticleDetail> findById(Long id) {
        var article = articleJpaRepository.findById(id)
                .orElseThrow(ARTICLE_NOT_FOUND::exception);
        return articleEntityMapper.toOptionalArticleDetail(article);
    }

    @Override
    public Article save(Article article) {
        var articleEntity = articleEntityMapper.toEntity(article);
        try{
            var newArticle = articleJpaRepository.save(articleEntity);
            articleJpaRepository.flush();
            return articleEntityMapper.toDomain(newArticle);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Article update(Article article) {
        var existArticle = articleJpaRepository.findById(article.getId())
                            .orElseThrow(ARTICLE_NOT_FOUND::exception);
        existArticle.prepareArticleEntityUpdate()
                .title(article.getTitle())
                .content(article.getContent())
                .totalLikes(article.getTotalLikes())
                .totalViews(article.getTotalViews())
                .totalShares(article.getTotalShares())
                .update();

        return articleEntityMapper.toDomain(existArticle);
    }

    @Override
    public void updateStatus(Long id, ArticleStatus articleStatus) {
        var article = articleJpaRepository.findById(id)
                    .orElseThrow(ARTICLE_NOT_FOUND::exception);
        article.prepareArticleEntityStatusUpdate()
                .status(ArticleEntityStatus.valueOf(articleStatus))
                .updateStatus();
        articleJpaRepository.save(article);
    }
}
