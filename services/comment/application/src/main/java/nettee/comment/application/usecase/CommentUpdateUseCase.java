package nettee.comment.application.usecase;

import nettee.comment.domain.Comment;

public interface CommentUpdateUseCase {
    Comment updateComment(Comment comment);
}
