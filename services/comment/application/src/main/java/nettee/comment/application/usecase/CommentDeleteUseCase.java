package nettee.comment.application.usecase;

import nettee.comment.domain.Comment;

public interface CommentDeleteUseCase {
    void deleteComment(Long id);
}
