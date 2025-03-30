package nettee.board.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import me.nettee.Board;
import me.nettee.type.BoardStatus;

public final class BoardCommandDto {

    private BoardCommandDto() {
    }

    @Builder
    public record BoardCreateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,

            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String content,

            @NotNull(message = "상태를 입력하십시오")
            BoardStatus status
    ) {
    }

    @Builder
    public record BoardCreateResponse(
            Board board
    ) {
    }
}
