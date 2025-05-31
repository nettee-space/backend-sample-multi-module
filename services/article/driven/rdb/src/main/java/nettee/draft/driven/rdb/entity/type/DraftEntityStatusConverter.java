package nettee.draft.driven.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nettee.draft.exception.DraftCommandErrorCode;
import nettee.draft.exception.DraftCommandException;

@Converter
public class DraftEntityStatusConverter implements AttributeConverter<DraftEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DraftEntityStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public DraftEntityStatus convertToEntityAttribute(Integer value) { return DraftEntityStatus.valueOf(value); }
}
