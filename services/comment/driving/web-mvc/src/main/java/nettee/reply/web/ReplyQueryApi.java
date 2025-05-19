package nettee.reply.web;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import nettee.reply.model.ReplyQueryModels.ReplyDetail;
import nettee.reply.application.service.ReplyQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyQueryApi {

    private final ReplyQueryService replyQueryService;

    // '답글 더보기' 요청
    @GetMapping("/{commentId}")
    public List<ReplyDetail> getRepliesByCommentIdAfter(
        @PathVariable("commentId") Long commentId,
        @RequestParam("after") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant createdAt
    ){
        return replyQueryService.getReplyListByCommentIdAfter(commentId, createdAt);
    }
}
