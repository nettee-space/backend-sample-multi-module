package nettee.draft.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DraftEntityStatusConverter implements AttributeConverter<DraftEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DraftEntityStatus status) { return status.getCode(); }

    @Override
    public DraftEntityStatus convertToEntityAttribute(Integer value) { return DraftEntityStatus.valueOf(value); }
}
