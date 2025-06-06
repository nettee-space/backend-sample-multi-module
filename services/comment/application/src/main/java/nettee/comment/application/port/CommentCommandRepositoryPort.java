package nettee.comment.application.port;

import java.util.List;
import nettee.comment.domain.Comment;
import nettee.comment.domain.type.CommentStatus;
import nettee.reply.domain.Reply;

public interface CommentCommandRepositoryPort {

    Comment save(Comment comment);

    Comment update(Comment comment);

    Comment findById(Long id);

    void updateStatus(Long id, CommentStatus status);

    void updateReplies(Long id, List<Reply> replyList);
}
