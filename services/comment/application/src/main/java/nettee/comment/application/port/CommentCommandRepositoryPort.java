package nettee.comment.application.port;

import nettee.comment.domain.Comment;
import nettee.comment.domain.type.CommentStatus;

public interface CommentCommandRepositoryPort {

    Comment save(Comment comment);

    Comment update(Comment comment);

    void updateStatus(Long id, CommentStatus status);
}
