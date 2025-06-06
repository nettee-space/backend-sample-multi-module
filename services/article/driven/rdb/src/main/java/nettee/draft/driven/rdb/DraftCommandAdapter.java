package nettee.draft.driven.rdb;

import lombok.RequiredArgsConstructor;
import nettee.draft.readmodel.DraftQueryModels.DraftDetail;
import nettee.draft.domain.Draft;
import nettee.draft.driven.rdb.entity.type.DraftEntityStatus;
import nettee.draft.driven.rdb.persistence.mapper.DraftEntityMapper;
import nettee.draft.application.port.DraftCommandPort;
import nettee.draft.domain.type.DraftStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static nettee.draft.exception.DraftCommandErrorCode.DEFAULT;
import static nettee.draft.exception.DraftCommandErrorCode.DRAFT_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class DraftCommandAdapter implements DraftCommandPort {
    private final DraftJpaRepository draftJpaRepository;
    private final DraftEntityMapper draftEntityMapper;

    @Override
    public Optional<DraftDetail> findById(Long id) {
        var draft = draftJpaRepository.findById(id)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return draftEntityMapper.toOptionalDraftDetail(draft);
    }

    @Override
    public Draft save(Draft draft) {
        var draftEntity = draftEntityMapper.toEntity(draft);
        try{
            var newDraft = draftJpaRepository.save(draftEntity);
            draftJpaRepository.flush();
            return draftEntityMapper.toDomain(newDraft);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Draft update(Draft draft) {
        var existDraft = draftJpaRepository.findById(draft.getId())
                            .orElseThrow(DRAFT_NOT_FOUND::exception);
        existDraft.prepareDraftEntityUpdate()
                .title(existDraft.getTitle())
                .content(existDraft.getContent())
                .update();

        return draftEntityMapper.toDomain(draftJpaRepository.save(existDraft));
    }

    @Override
    public void updateStatus(Long id, DraftStatus draftStatus) {
        var draft = draftJpaRepository.findById(id)
                    .orElseThrow(DRAFT_NOT_FOUND::exception);
        draft.prepareDraftEntityStatusUpdate()
                .status(DraftEntityStatus.valueOf(draftStatus))
                .updateStatus();
        draftJpaRepository.save(draft);
    }
}
