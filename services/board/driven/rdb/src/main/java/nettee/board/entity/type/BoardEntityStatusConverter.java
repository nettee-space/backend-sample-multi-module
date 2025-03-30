package nettee.board.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoardEntityStatusConverter implements AttributeConverter<BoardEntityStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BoardEntityStatus status) {
        return status.getCode();
    }

    @Override
    public BoardEntityStatus convertToEntityAttribute(Integer value) {
        return BoardEntityStatus.valueOf(value);
    }
}
