package nettee.reply.driven.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class ReplyEntityStatusConverter implements AttributeConverter<ReplyEntityStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ReplyEntityStatus status) {
        return status.getCode();
    }

    @Override
    public ReplyEntityStatus convertToEntityAttribute(Integer value) {
        return ReplyEntityStatus.valueOf(value);
    }
}