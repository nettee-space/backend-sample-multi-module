package nettee.reply.port;

import nettee.reply.Reply;
import nettee.reply.type.ReplyStatus;

public interface ReplyCommandRepositoryPort {

    Reply save(Reply reply);

    Reply update(Reply reply);

    void updateStatus(Long id, ReplyStatus status);
}
