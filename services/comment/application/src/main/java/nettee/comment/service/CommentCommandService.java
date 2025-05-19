package nettee.comment.service;

import lombok.RequiredArgsConstructor;
import nettee.comment.Comment;
import nettee.comment.port.CommentCommandRepositoryPort;
import nettee.comment.type.CommentStatus;
import nettee.comment.usecase.CommentCreateUseCase;
import nettee.comment.usecase.CommentDeleteUseCase;
import nettee.comment.usecase.CommentUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCommandService implements CommentCreateUseCase, CommentUpdateUseCase, CommentDeleteUseCase {

    private final CommentCommandRepositoryPort commentCommandRepositoryPort;

    @Override
    public Comment createComment(Comment comment) {
        return commentCommandRepositoryPort.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentCommandRepositoryPort.update(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentCommandRepositoryPort.updateStatus(id, CommentStatus.REMOVED);
    }
}
