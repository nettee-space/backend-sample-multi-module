package nettee.draft.driven.rdb.persistence.mapper;

import nettee.draft.driven.rdb.entity.DraftEntity;
import nettee.draft.readmodel.DraftQueryModels.DraftDetail;
import nettee.draft.readmodel.DraftQueryModels.DraftSummary;
import nettee.draft.domain.Draft;
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
