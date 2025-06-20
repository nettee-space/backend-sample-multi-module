package nettee.comment.application.service;

import lombok.RequiredArgsConstructor;
import nettee.comment.application.usecase.CommentDeleteUseCase;
import nettee.comment.domain.Comment;
import nettee.comment.application.port.CommentCommandRepositoryPort;
import nettee.comment.domain.type.CommentStatus;
import nettee.comment.application.usecase.CommentCreateUseCase;
import nettee.comment.application.usecase.CommentUpdateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandService implements CommentCreateUseCase, CommentUpdateUseCase,
    CommentDeleteUseCase {

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
