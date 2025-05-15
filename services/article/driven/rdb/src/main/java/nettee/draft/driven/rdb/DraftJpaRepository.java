package nettee.draft.driven.rdb;

import nettee.draft.driven.rdb.entity.DraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftJpaRepository extends JpaRepository<DraftEntity, Long> {
}
