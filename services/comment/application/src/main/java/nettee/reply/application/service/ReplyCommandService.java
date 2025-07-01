package nettee.reply.application.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import nettee.comment.application.port.CommentCommandRepositoryPort;
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
    private final CommentCommandRepositoryPort commentCommandRepositoryPort;

    @Override
    public Reply createReply(Reply reply) {
        var saveReply = replyCommandRepositoryPort.save(reply);
        var comment = commentCommandRepositoryPort.findById(reply.getCommentId());

        List<Reply> replies = comment.getReplies();

        // 답글의 개수가 10 미만이면, 해당 comment.replies에 reply를 추가합니다.
        if(replies.size() < 10) {
            replies.add(saveReply);
            commentCommandRepositoryPort.updateReplies(comment.getId(), replies);
        }

        return saveReply;
    }

    @Override
    public void deleteReply(Long id) {
        var reply = replyCommandRepositoryPort.findById(id);
        var comment = commentCommandRepositoryPort.findById(reply.getCommentId());

        var replies = comment.getReplies();

        // 1. comment.replies에 지우고자 하는 reply가 존재할 경우, comment도 업데이트 해야합니다.
        // 1-1) comment.replies에 지우고자 하는 reply가 존재하는지 확인합니다.
        boolean removed = replies.removeIf(r -> r.getId().equals(reply.getId()));

        // 1-2) 지우고자 하는 reply가 있었다면
        if(removed) {

            // 1-3) comment.replies의 크기가 10미만이 되었으므로 다음 reply가 더 있는지 확인
            var nextReply = replyCommandRepositoryPort.findFirstByCommentIdAfter(
                comment.getId(), replies.getLast().getCreatedAt());

            // 1-4) 다음 reply가 존재한다면 comment.replies에 추가해줍니다.
            if(nextReply != null) {
                replies.add(nextReply);
            }
            // 1-5) comment.replies에 변화가 있었으므로 업데이트합니다.
            commentCommandRepositoryPort.updateReplies(comment.getId(),replies);
        }

        // 2. reply의 status를 REMOVE로 설정합니다.
        replyCommandRepositoryPort.updateStatus(id, ReplyStatus.REMOVED);
    }

    @Override
    public Reply updateReply(Reply reply) {
        var updateReply = replyCommandRepositoryPort.update(reply);
        var comment = commentCommandRepositoryPort.findById(updateReply.getCommentId());

        List<Reply> replies = comment.getReplies();

        // 1. comment.replies에 동일한 reply가 존재할 경우, comment도 업데이트 해야합니다.
        // 1-1) comment.replies에 동일한 reply가 존재하는지 확인합니다.
        boolean updated = false;
        for(int i = 0; i < replies.size(); i++) {
            if(Objects.equals(replies.get(i).getId(), reply.getId())) {
                replies.set(i, updateReply);
                updated = true;
                break;
            }
        }

        // 1-2) 존재한다면, comment 역시 업데이트 해야합니다.
        if(updated) {
            commentCommandRepositoryPort.updateReplies(comment.getId(),replies);
        }

        return updateReply;
    }
}
