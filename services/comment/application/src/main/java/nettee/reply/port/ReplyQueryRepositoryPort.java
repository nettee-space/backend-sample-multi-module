package nettee.reply.port;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.reply.Reply;

public interface ReplyQueryRepositoryPort {

    Optional<Reply> findById(Long id);

    // comment_id에 해당하는 최초 reply 목록 조회
    List<Reply> findPageByCommentId(Long commentId, int offset, int size);

    // comment_id, 현재 페이지의 마지막 이후의 reply 목록 조회
    List<Reply> findPageByCommentIdAfter(Long commentId, Instant createdAt, int size);
}
