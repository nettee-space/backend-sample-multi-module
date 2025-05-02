package nettee.post.port;

import nettee.post.Draft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.Set;

public interface DraftQueryPort {
    Optional<Draft> findById(Long id);
    Page<Draft> findAll(Pageable pageable);

}
