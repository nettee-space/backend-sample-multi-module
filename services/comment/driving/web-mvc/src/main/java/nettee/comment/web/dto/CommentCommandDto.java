package nettee.comment.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import nettee.comment.Comment;
import nettee.comment.type.CommentStatus;

public class CommentCommandDto {

    private CommentCommandDto() {

    }

    @Builder
    public record CommentCreateCommand(
        @NotNull(message = "boardId를 입력하십시오")
        Long boardId,
        @NotBlank(message = "본문을 입력하십시오")
        String content,
        @NotNull(message = "상태를 입력하십시오")
        CommentStatus status
    ) {

    }

    @Builder
    public record CommentUpdateCommand(
        @NotNull(message = "id를 입력하십시오")
        Long id,
        @NotBlank(message = "본문을 입력하십시오")
        String content,
        @NotNull(message = "상태를 입력하십시오")
        CommentStatus status
    ){

    }

    @Builder
    public record CommentCommandResponse(
        Comment comment
    ){

    }

}
