package nettee.draft.driven.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nettee.draft.exception.DraftQueryErrorCode;
import nettee.draft.exception.DraftQueryException;

@Converter
public class DraftEntityStatusConverter implements AttributeConverter<DraftEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DraftEntityStatus status) {
        if (status == null) {
            throw new DraftQueryException(DraftQueryErrorCode.DEFAULT);
        }
        return status.getCode();
    }

    @Override
    public DraftEntityStatus convertToEntityAttribute(Integer value) { return DraftEntityStatus.valueOf(value); }
}
