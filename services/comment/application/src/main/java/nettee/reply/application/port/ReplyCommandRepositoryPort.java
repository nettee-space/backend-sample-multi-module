package nettee.reply.application.port;

import nettee.reply.domain.Reply;
import nettee.reply.domain.type.ReplyStatus;

public interface ReplyCommandRepositoryPort {

    Reply save(Reply reply);

    Reply update(Reply reply);

    void updateStatus(Long id, ReplyStatus status);
}
