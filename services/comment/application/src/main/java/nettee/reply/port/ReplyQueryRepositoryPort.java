package nettee.reply.port;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.reply.Reply;
import nettee.reply.model.ReplyQueryModels.ReplyDetail;

public interface ReplyQueryRepositoryPort {

    Optional<ReplyDetail> findById(Long id);

    // comment_id에 해당하는 reply 목록 조회
    List<ReplyDetail> findPageByCommentId(Long commentId, int offset, int size);

    // comment_id, 현재 페이지의 마지막 이후의 reply 목록 조회
    List<ReplyDetail> findPageByCommentIdAfter(Long commentId, Instant createdAt, int size);
}
