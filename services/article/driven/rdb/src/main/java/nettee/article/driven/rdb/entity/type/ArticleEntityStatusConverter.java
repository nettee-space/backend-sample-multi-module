package nettee.article.driven.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nettee.article.exception.ArticleCommandErrorCode;
import nettee.article.exception.ArticleCommandException;

@Converter
public class ArticleEntityStatusConverter implements AttributeConverter<ArticleEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ArticleEntityStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public ArticleEntityStatus convertToEntityAttribute(Integer value) { return ArticleEntityStatus.valueOf(value); }
}
