package nettee.comment.service;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import nettee.comment.Comment;
import nettee.comment.port.CommentQueryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentQueryRepositoryPort commentQueryRepositoryPort;

    public List<Comment> getCommentListByBoardId(Long boardId) {
        return commentQueryRepositoryPort.findPageByBoardId(boardId, 0, 10);
    }

    public List<Comment> getCommentListByBoardIdAfter(Long boardId, Instant createdAt) {
        return commentQueryRepositoryPort.findPageByBoardIdAfter(boardId, createdAt, 10);
    }

}
