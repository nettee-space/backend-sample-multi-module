package nettee.board.driving.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.board.domain.type.BoardStatus;
import nettee.board.application.usecase.BoardCreateUseCase;
import nettee.board.driving.web.dto.BoardCommandDto.BoardCreateCommand;
import nettee.board.driving.web.dto.BoardCommandDto.BoardCreateResponse;
import nettee.board.driving.web.mapper.BoardDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardCommandApi {
    private final BoardCreateUseCase boardCreateUseCase;
    private final BoardDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardCreateResponse create(
            @RequestBody @Valid BoardCreateCommand requestBody
    ) {
        var board = mapper.toDomain(requestBody, BoardStatus.ACTIVE);

        return BoardCreateResponse.builder()
                .board(boardCreateUseCase.createBoard(board))
                .build();
    }
}
