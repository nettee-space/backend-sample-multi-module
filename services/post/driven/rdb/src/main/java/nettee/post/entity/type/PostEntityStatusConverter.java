package nettee.post.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PostEntityStatusConverter implements AttributeConverter<PostEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PostEntityStatus status) { return status.getCode(); }

    @Override
    public PostEntityStatus convertToEntityAttribute(Integer value) { return PostEntityStatus.valueOf(value); }
}
