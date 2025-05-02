package nettee.draft.port;

import nettee.post.Draft;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface DraftQueryPort {
    Optional<Draft> findById(Long id);
    Page<Draft> findAll(Pageable pageable);

}
