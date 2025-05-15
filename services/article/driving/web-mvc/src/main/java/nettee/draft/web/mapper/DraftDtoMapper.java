package nettee.draft.web.mapper;

import nettee.draft.domain.Draft;
import nettee.draft.DraftQueryModels.DraftDetail;
import nettee.draft.web.dto.DraftCommandDto.DraftCreateCommand;
import nettee.draft.web.dto.DraftCommandDto.DraftUpdateCommand;
import nettee.draft.web.dto.DraftCommandDto.DraftUpdateTotalLikesCommand;
import nettee.draft.web.dto.DraftCommandDto.DraftUpdateTotalSharesCommand;
import nettee.draft.web.dto.DraftCommandDto.DraftUpdateTotalViewsCommand;
import nettee.draft.web.dto.DraftQueryDto.DraftDetailResponse;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftDtoMapper {
    Draft toDomain(DraftCreateCommand command);
    Draft toDomain(Long id, DraftUpdateCommand command);
    Draft toDomain(Long id, DraftUpdateTotalSharesCommand command);
    Draft toDomain(Long id, DraftUpdateTotalLikesCommand command);
    Draft toDomain(Long id, DraftUpdateTotalViewsCommand command);
    DraftDetailResponse toDtoDetail(Optional<DraftDetail> board);
}
