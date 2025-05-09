package nettee.article.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ArticleEntityStatusConverter implements AttributeConverter<ArticleEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ArticleEntityStatus status) { return status.getCode(); }

    @Override
    public ArticleEntityStatus convertToEntityAttribute(Integer value) { return ArticleEntityStatus.valueOf(value); }
}
