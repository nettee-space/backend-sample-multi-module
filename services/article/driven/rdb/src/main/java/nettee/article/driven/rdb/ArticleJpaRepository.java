package nettee.article.driven.rdb;

import nettee.article.driven.rdb.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
}
