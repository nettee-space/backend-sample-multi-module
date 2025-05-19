package nettee.reply.service;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import nettee.reply.Reply;
import nettee.reply.port.ReplyQueryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyQueryService {

    private final ReplyQueryRepositoryPort replyQueryRepositoryPort;

    public List<Reply> getReplyListByCommentId(Long commentId) {
        return replyQueryRepositoryPort.findPageByCommentId(commentId, 0, 10);
    }

    public List<Reply> getReplyListByCommentIdAfter(Long commentId, Instant createdAt) {
        return replyQueryRepositoryPort.findPageByCommentIdAfter(commentId, createdAt, 10);
    }

}
