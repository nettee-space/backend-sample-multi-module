package nettee.comment.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.comment.application.usecase.CommentCreateUseCase;
import nettee.comment.application.usecase.CommentDeleteUseCase;
import nettee.comment.application.usecase.CommentUpdateUseCase;
import nettee.comment.web.dto.CommentCommandDto.CommentCommandResponse;
import nettee.comment.web.dto.CommentCommandDto.CommentCreateCommand;
import nettee.comment.web.dto.CommentCommandDto.CommentUpdateCommand;
import nettee.comment.web.mapper.CommentDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentCommandApi {

    private final CommentCreateUseCase commentCreateUseCase;
    private final CommentUpdateUseCase commentUpdateUseCase;
    private final CommentDeleteUseCase commentDeleteUseCase;
    private final CommentDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentCommandResponse create(
        @RequestBody @Valid CommentCreateCommand command
    ) {
        var comment = mapper.toDomain(command);
        return CommentCommandResponse.builder()
            .comment(commentCreateUseCase.createComment(comment))
            .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentCommandResponse update(
        @PathVariable("id") Long id,
        @RequestBody @Valid CommentUpdateCommand command
    ) {
        var comment = mapper.toDomain(command);
        return CommentCommandResponse.builder()
            .comment(commentUpdateUseCase.updateComment(comment))
            .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        commentDeleteUseCase.deleteComment(id);
    }
}
