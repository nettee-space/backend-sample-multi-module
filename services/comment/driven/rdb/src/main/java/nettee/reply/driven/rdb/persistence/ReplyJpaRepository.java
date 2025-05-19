package nettee.reply.driven.rdb.persistence;

import nettee.reply.driven.rdb.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyJpaRepository extends JpaRepository<ReplyEntity, Long> {

}
