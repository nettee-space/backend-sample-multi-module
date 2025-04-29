package nettee.post.port;

import nettee.post.DraftPost;
import nettee.post.type.DraftStatus;
import java.util.Optional;

public interface DraftCommandPort {
    Optional<DraftPost> findById(Long id);
    DraftPost create(DraftPost post);
    DraftPost update(DraftPost post);
    void updateStatus(Long id, DraftStatus draftStatus);
}
