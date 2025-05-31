package nettee.comment.driven.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CommentEntityStatusConverter implements AttributeConverter<CommentEntityStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CommentEntityStatus status) {
        return status.getCode();
    }

    @Override
    public CommentEntityStatus convertToEntityAttribute(Integer value) {
        return CommentEntityStatus.valueOf(value);
    }
}