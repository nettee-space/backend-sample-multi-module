package nettee.post.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nettee.post.usecase.PostCreateUseCase;
import nettee.post.usecase.PostDeleteUseCase;
import nettee.post.usecase.PostUpdateUseCase;
import nettee.post.web.dto.PostCommandDto.PostUpdateCommand;
import nettee.post.web.dto.PostCommandDto.PostCreateCommand;
import nettee.post.web.dto.PostCommandDto.PostCommandResponse;
import nettee.post.web.mapper.PostDtoMapper;
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
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostCommandApi {
    private final PostCreateUseCase postCreateUseCase;
    private final PostUpdateUseCase postUpdateUseCase;
    private final PostDeleteUseCase postDeleteUseCase;
    private final PostDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostCommandResponse create(
            @RequestBody @Valid PostCreateCommand postCreateCommand
            ) {
        var post = mapper.toDomain(postCreateCommand);
        return PostCommandResponse.builder()
                .post(postCreateUseCase.createPost(post))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostCommandResponse updatePost(
            @PathVariable("id") Long id,
            @RequestBody @Valid PostUpdateCommand postUpdateCommand
    ) {
        var post = mapper.toDomain(id, postUpdateCommand);

        return PostCommandResponse.builder()
                .post(postUpdateUseCase.updatePost(post))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable("id") Long id) {
        postDeleteUseCase.deletePost(id);
    }

}
