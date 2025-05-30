package nettee.comment.application.usecase;

import nettee.comment.domain.Comment;

public interface CommentCreateUseCase {
    Comment createComment(Comment comment);
}
