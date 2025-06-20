package nettee.reply.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nettee.reply.domain.Reply;
import nettee.reply.application.port.ReplyCommandRepositoryPort;
import nettee.reply.domain.type.ReplyStatus;
import nettee.reply.application.usecase.ReplyCreateUseCase;
import nettee.reply.application.usecase.ReplyDeleteUseCase;
import nettee.reply.application.usecase.ReplyUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
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
