package nettee.comment.port;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.comment.Comment;

public interface CommentQueryRepositoryPort {
    Optional<Comment> findById(Long id);

    // board_id에 해당하는 최초 comment 10개 조회
    List<Comment> find10ByPostId(Long boardId);

    // board_id, 현재 페이지의 마지막 이후의 comment 10개 조회
    List<Comment> find10ByPostIdAfterCommentId(Long boardId, Instant createdAt);
}
