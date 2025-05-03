package nettee.draft.persistence.mapper;

import nettee.draft.entity.DraftEntity;
import nettee.draft.DraftQueryModels.DraftDetail;
import nettee.draft.DraftQueryModels.DraftSummary;
import nettee.post.Draft;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftEntityMapper {
    Draft toDomain(DraftEntity draftEntity);
    DraftEntity toEntity(Draft draft);
    DraftDetail toDraftDetail(DraftEntity draftEntity);
    DraftSummary toDraftSummary(DraftEntity draftEntity);
    default Optional<Draft> toOptionalDomain(DraftEntity draftEntity) {
        return Optional.ofNullable(toDomain(draftEntity));
    }
    default Optional<DraftDetail> toOptionalDraftDetail(DraftEntity draftEntity) {
        return Optional.ofNullable(toDraftDetail(draftEntity));
    }
    default Optional<DraftSummary> toOptionalDraftSummary(DraftEntity draftEntity) {
        return Optional.ofNullable(toDraftSummary(draftEntity));
    }

}
