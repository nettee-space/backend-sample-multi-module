package nettee.reply.driven.rdb.persistence;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.reply.driven.rdb.entity.ReplyEntity;
import nettee.reply.driven.rdb.entity.type.ReplyEntityStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyJpaRepository extends JpaRepository<ReplyEntity, Long> {
    @Query("SELECT r FROM reply r WHERE r.commentId = :commentId AND r.createdAt > :createdAt AND r.status =:status ORDER BY r.createdAt")
    List<ReplyEntity> findByCommentIdAndCreatedAtAfter(
        @Param("commentId") Long commentId,
        @Param("createdAt") Instant createdAt,
        @Param("status") ReplyEntityStatus status,
        Pageable pageable);
}
