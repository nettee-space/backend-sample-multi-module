package nettee.comment.port;

import nettee.comment.Comment;

public interface CommentCommandRepositoryPort {

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(Comment comment);
}
