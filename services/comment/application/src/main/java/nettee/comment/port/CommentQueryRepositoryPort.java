package nettee.comment.port;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import nettee.comment.Comment;
import nettee.comment.model.CommentQueryModels.CommentDetail;

public interface CommentQueryRepositoryPort {
    Optional<CommentDetail> findById(Long id);

    // board_id에 해당하는 comment 목록 조회
    List<CommentDetail> findPageByBoardId(Long boardId, int offset, int size);
}
