package nettee.draft.driving.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.draft.domain.Draft;
import nettee.draft.driving.web.mapper.DraftDtoMapper;
import nettee.draft.application.usecase.DraftCreateUseCase;
import nettee.draft.application.usecase.DraftDeleteUseCase;
import nettee.draft.application.usecase.DraftUpdateUseCase;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftCommandResponse;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftCreateCommand;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftUpdateCommand;
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
@RequestMapping("drafts")
@RequiredArgsConstructor
public class DraftCommandApi {
    private final DraftCreateUseCase draftCreateUseCase;
    private final DraftUpdateUseCase draftUpdateUseCase;
    private final DraftDeleteUseCase draftDeleteUseCase;
    private final DraftDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DraftCommandResponse create(@RequestBody @Valid DraftCreateCommand draftCreateCommand) {
        var draft = Draft.of(draftCreateCommand.title(), draftCreateCommand.content());
        return DraftCommandResponse.builder()
                .draft(draftCreateUseCase.createDraft(draft))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DraftCommandResponse updateDraft(
            @PathVariable("id") Long id,
            @RequestBody @Valid DraftUpdateCommand draftUpdateCommand
    ) {
        var draft = mapper.toDomain(id, draftUpdateCommand);

        return DraftCommandResponse.builder()
                .draft(draftUpdateUseCase.updateDraft(draft))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable("id") Long id) {
        draftDeleteUseCase.deleteDraft(id);
    }

}
