package nettee.comment.port;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.comment.Comment;

public interface CommentQueryRepositoryPort {
    Optional<Comment> findById(Long id);

    // board_id에 해당하는 comment 목록 조회
    List<Comment> findPageByBoardId(Long boardId, int offset, int size);

    // board_id, 현재 페이지의 마지막 생성일 이후의 comment 목록 조회
    List<Comment> findPageByBoardIdAfter(Long boardId, Instant createdAt, int size);
}
