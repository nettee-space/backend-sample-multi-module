package nettee.reply.persistence;

import nettee.reply.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyJpaRepository extends JpaRepository<ReplyEntity, Long> {

}
