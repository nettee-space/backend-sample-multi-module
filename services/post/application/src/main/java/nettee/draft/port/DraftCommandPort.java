package nettee.draft.port;

import nettee.post.Draft;
import nettee.post.type.DraftStatus;
import java.util.Optional;

public interface DraftCommandPort {
    Optional<Draft> findById(Long id);
    Draft create(Draft post);
    Draft update(Draft post);
    void updateStatus(Long id, DraftStatus draftStatus);
}
