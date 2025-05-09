package nettee.draft;

import nettee.draft.entity.DraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftJpaRepository extends JpaRepository<DraftEntity, Long> {
}
