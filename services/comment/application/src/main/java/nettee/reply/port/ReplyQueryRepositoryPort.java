package nettee.reply.port;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.reply.Reply;

public interface ReplyQueryRepositoryPort {

    Optional<Reply> findById(Long id);

    // comment_id에 해당하는 최초 reply 10개 조회
    List<Reply> find10ByCommentId(Long commentId);

    // comment_id, 현재 페이지의 마지막 이후의 reply 10개 조회
    List<Reply> find10ByCommentIdAfter(Long commentId, Instant createdAt);
}
