package nettee.comment.application.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import nettee.comment.model.CommentQueryModels.CommentDetail;
import nettee.comment.application.port.CommentQueryRepositoryPort;
import nettee.reply.application.port.ReplyQueryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentQueryRepositoryPort commentQueryRepositoryPort;
    private final ReplyQueryRepositoryPort replyQueryRepositoryPort;

    public List<CommentDetail> getCommentsByBoardId(Long boardId) {
        var comments = commentQueryRepositoryPort.findPageByBoardId(boardId, 0, 10);

        // comment별로 reply를 10개씩 가져옴
        // 현재 N+1 이므로, 최대 11(1+10)개의 쿼리를 발생시킴
        var result = comments.stream()
            .map(comment -> {
                var replies = replyQueryRepositoryPort.findPageByCommentId(comment.id(), 0, 10);

                return CommentDetail.builder()
                    .id(comment.id())
                    .content(comment.content())
                    .status(comment.status())
                    .createdAt(comment.createdAt())
                    .updatedAt(comment.updatedAt())
                    .replies(replies)
                    .build();
            }).collect(Collectors.toList());

        return result;
    }

}
