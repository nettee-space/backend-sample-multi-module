package nettee.comment.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nettee.comment.model.CommentQueryModels.CommentDetail;
import nettee.comment.application.service.CommentQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentQueryApi {

    private final CommentQueryService commentQueryService;

    @GetMapping()
    public List<CommentDetail> getCommentsByBoardId(@RequestParam("boardId") Long boardId) {
        return commentQueryService.getCommentsByBoardId(boardId);
    }

    @GetMapping("/json")
    public List<CommentDetail> getJsonByBoardId(@RequestParam("boardId") Long boardId) {
        return commentQueryService.getJsonByBoardId(boardId);
    }

}
