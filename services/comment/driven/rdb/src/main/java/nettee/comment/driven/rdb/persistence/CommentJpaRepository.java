package nettee.comment.driven.rdb.persistence;

import nettee.comment.driven.rdb.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

}
