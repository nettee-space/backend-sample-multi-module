package nettee.reply.service;

import lombok.RequiredArgsConstructor;
import nettee.reply.Reply;
import nettee.reply.port.ReplyCommandRepositoryPort;
import nettee.reply.type.ReplyStatus;
import nettee.reply.usecase.ReplyCreateUseCase;
import nettee.reply.usecase.ReplyDeleteUseCase;
import nettee.reply.usecase.ReplyUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyCommandService implements ReplyCreateUseCase, ReplyUpdateUseCase, ReplyDeleteUseCase {

    private final ReplyCommandRepositoryPort replyCommandRepositoryPort;

    @Override
    public Reply createReply(Reply reply) {
        return replyCommandRepositoryPort.save(reply);
    }

    @Override
    public void deleteReply(Long id) {
        replyCommandRepositoryPort.updateStatus(id, ReplyStatus.REMOVED);
    }

    @Override
    public Reply updateReply(Reply reply) {
        return replyCommandRepositoryPort.update(reply);
    }
}
