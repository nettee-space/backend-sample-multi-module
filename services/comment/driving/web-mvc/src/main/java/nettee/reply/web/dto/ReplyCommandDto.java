package nettee.reply.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import nettee.reply.domain.Reply;
import nettee.reply.domain.type.ReplyStatus;

public class ReplyCommandDto {

    private ReplyCommandDto() {

    }

    @Builder
    public record ReplyCreateCommand(
        @NotNull(message = "commentId를 입력하십시오")
        Long commentId,
        @NotBlank(message = "본문을 입력하십시오")
        String content,
        @NotNull(message = "상태를 입력하십시오")
        ReplyStatus status
    ){

    }

    @Builder
    public record ReplyUpdateCommand(
        @NotNull(message = "id를 입력하십시오")
        Long id,
        @NotBlank(message = "본문을 입력하십시오")
        String content,
        @NotNull(message = "상태를 입력하십시오")
        ReplyStatus status
    ){

    }

    @Builder
    public record ReplyCommandResponse(
        Reply reply
    ) {

    }

}
