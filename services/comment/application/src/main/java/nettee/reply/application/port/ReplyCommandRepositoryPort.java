package nettee.reply.application.port;

import java.time.Instant;
import nettee.reply.domain.Reply;
import nettee.reply.domain.type.ReplyStatus;

public interface ReplyCommandRepositoryPort {

    Reply save(Reply reply);

    Reply update(Reply reply);

    Reply findById(Long id);

    void updateStatus(Long id, ReplyStatus status);

    Reply findFirstByCommentIdAfter(Long commentId, Instant createdAt);
}
