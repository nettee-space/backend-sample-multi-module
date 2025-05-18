package nettee.comment.port;

import nettee.comment.Comment;
import nettee.comment.type.CommentStatus;

public interface CommentCommandRepositoryPort {

    Comment save(Comment comment);

    Comment update(Comment comment);

    void updateStatus(Long id, CommentStatus status);
}
