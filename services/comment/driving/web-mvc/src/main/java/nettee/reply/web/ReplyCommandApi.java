package nettee.reply.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.reply.application.usecase.ReplyCreateUseCase;
import nettee.reply.application.usecase.ReplyDeleteUseCase;
import nettee.reply.application.usecase.ReplyUpdateUseCase;
import nettee.reply.web.dto.ReplyCommandDto.ReplyCommandResponse;
import nettee.reply.web.dto.ReplyCommandDto.ReplyCreateCommand;
import nettee.reply.web.dto.ReplyCommandDto.ReplyUpdateCommand;
import nettee.reply.web.mapper.ReplyDtoMapper;
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
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyCommandApi {

    private final ReplyCreateUseCase replyCreateUseCase;
    private final ReplyUpdateUseCase replyUpdateUseCase;
    private final ReplyDeleteUseCase replyDeleteUseCase;
    private final ReplyDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReplyCommandResponse create(
        @RequestBody @Valid ReplyCreateCommand command
    ) {
        var reply = mapper.toDomain(command);
        return ReplyCommandResponse.builder()
            .reply(replyCreateUseCase.createReply(reply))
            .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReplyCommandResponse update(
        @PathVariable("id") Long id,
        @RequestBody @Valid ReplyUpdateCommand command
    ) {
        var reply = mapper.toDomain(command);
        return ReplyCommandResponse.builder()
            .reply(replyUpdateUseCase.updateReply(reply))
            .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        replyDeleteUseCase.deleteReply(id);
    }
}
