package nettee.reply.port;

import nettee.reply.Reply;

public interface ReplyCommandRepositoryPort {

    Reply save(Reply reply);

    Reply update(Reply reply);

    void delete(Reply reply);
}
