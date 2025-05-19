package nettee.comment.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nettee.comment.model.CommentQueryModels.CommentDetail;
import nettee.comment.service.CommentQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentQueryApi {

    private final CommentQueryService commentQueryService;

    @GetMapping("/{boardId}")
    public List<CommentDetail> getCommentsByBoardId(@PathVariable("boardId") Long boardId) {
        return commentQueryService.getCommentsByBoardId(boardId);
    }

}
