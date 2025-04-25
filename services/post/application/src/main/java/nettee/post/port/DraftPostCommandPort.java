package nettee.post.port;


import nettee.post.DraftPost;
import nettee.post.type.DraftPostStatus;

import java.util.Optional;

public interface DraftPostCommandPort {
    Optional<DraftPost> findById(Long id);
    DraftPost create(DraftPost post);
    DraftPost update(DraftPost post);
    void updateStatus(Long id, DraftPostStatus postStatus);
}
