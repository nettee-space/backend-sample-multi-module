package nettee.post.port;

import nettee.post.DraftPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface DraftPostQueryPort {
    Optional<DraftPost> findById(Long id);
    Page<DraftPost> findAll(Pageable pageable);

}
